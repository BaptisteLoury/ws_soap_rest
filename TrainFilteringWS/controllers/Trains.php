<?php
    require_once 'controllers/db.php';

    function get_trains($condition) {
        $query = "SELECT * FROM TRAINS WHERE 1 $condition";
        $db = new Database();
        return $db->select($query);
    }

    function get_train_info($args) {
        if (count($args) == 1) {
            if (is_numeric($args[0])) {
                $id = $args[0];
                $train = get_trains("AND TRAIN_ID = $id");
                if (count($train) == 0) {
                    header('HTTP/1.1 404 Not Found');
                    echo "404 Not Found";
                    return;
                }
                header('HTTP/1.1 200 OK');
                header('Content-Type: application/json');
                echo json_encode($train[0]);
            }
        }elseif (count($args) == 0) {
            $train = get_trains(null);
            header('HTTP/1.1 200 OK');
            header('Content-Type: application/json');
            echo json_encode($train);
        }
    }

    function get_train_info_destination($args){
        $condition = "";
        $db = new Database();
        $c = $db->select("SELECT DISTINCT TRAIN_SOURCE FROM TRAINS UNION SELECT DISTINCT TRAIN_DESTINATION FROM TRAINS");
        $citys = array();
        foreach ($c as $value) {
            $citys[] = $value['TRAIN_SOURCE'];
        }
        if (!(count($args) == 4 || count($args) == 6)) {
            header('HTTP/1.1 412 Precondition Failed');
            echo "412 Precondition Failed, parameters are not correct";
            return;
        }
        if (count($args) == 6) {
            if ($args[4] == 'departure' || $args[4] == 'arrival') {
                $datetime = explode('_', $args[5]);

                $date = $datetime[0];
                $time = $datetime[1];
                $date = date('Y-m-d', strtotime($date));
                if ($args[4] == 'departure') {
                    $condition .= "AND TRAIN_DEPARTURE_DATE = '$date' AND TRAIN_DEPARTURE_TIME = '$time' ";
                }else{
                    $condition .= "AND TRAIN_ARRIVAL_DATE = '$date' AND TRAIN_ARRIVAL_TIME = '$time' ";
                }

            }else{
                header('HTTP/1.1 412 Precondition Failed');
                echo "412 Precondition Failed, please use departure or arrival in the url";
                return;
            }
        }
        if ($args[0] == 'from' && $args[2] == 'to') {
            if (in_array($args[1], $citys) && in_array($args[3], $citys)) {
                $source = $args[1];
                $destination = $args[3];
                $condition .= "AND TRAIN_SOURCE = '$source' AND TRAIN_DESTINATION = '$destination' ";
                $train = get_trains($condition);
                header('HTTP/1.1 200 OK');
                header('Content-Type: application/json');
                echo json_encode($train);
            }else{
                header('HTTP/1.1 404 Not Found');
                echo "404 Not Found, please use a valid city name";
            }
        }else{
            header('HTTP/1.1 412 Precondition Failed');
            echo "412 Precondition Failed, please use from and to parameters to specify the source and destination of the train";
        }
    }

    function add_reservation_json($args) {
        $json = file_get_contents('php://input');
        $data = json_decode($json, true);
        $_POST = $data;

        if (!isset($_POST['train_id']) || !isset($_POST['first_name']) || !isset($_POST['last_name'])) {
            header('HTTP/1.1 412 Precondition Failed');
            echo "412 Precondition Failed";
            return;
        }
        if (!is_numeric($_POST['train_id'])) {
            header('HTTP/1.1 412 Precondition Failed train id not numeric');
            echo "412 Precondition Failed";
            return;
        }
        $train_id = $_POST['train_id'];
        $first_name = htmlspecialchars($_POST['first_name']);
        $last_name = htmlspecialchars($_POST['last_name']);

        $db = new Database();
        $train = $db->select("SELECT * FROM TRAINS WHERE TRAIN_ID = $train_id");
        if (count($train) == 0) {
            header('HTTP/1.1 404 Not Found');
            echo "404 Not Found";
            return;
        }
        $train = $train[0];

        $personsintrain = $db->select("SELECT COUNT(*) AS p FROM RESERVATIONS WHERE TRAIN_ID = $train_id");
        $personsintrain = $personsintrain[0]['p'];
        if ($personsintrain >= $train['TRAIN_AVAILABILITY']) {
            header('HTTP/1.1 405 Method Not Allowed');
            echo "405 Method Not Allowed, full seats";
            return;
        }

        $db->execute("INSERT INTO RESERVATIONS (RESERVATION_FIRSTNAME, RESERVATION_LASTNAME, TRAIN_ID) VALUES ('$first_name', '$last_name', $train_id)");
        $reservation = $db->select("SELECT MAX(RESERVATION_ID) AS RESERVATION_ID FROM RESERVATIONS WHERE RESERVATION_FIRSTNAME = '$first_name' AND RESERVATION_LASTNAME = '$last_name' AND TRAIN_ID = $train_id")[0];
        header('HTTP/1.1 200 OK');
        header('Content-Type: application/json');
        echo json_encode($reservation);
    }

    function get_reservation($args) {
        if (count($args) == 1) {
            if (is_numeric($args[0])) {
                $id = $args[0];
                $db = new Database();
                $reservation = $db->select("SELECT * FROM RESERVATIONS WHERE RESERVATION_ID = $id")[0];
                if (count($reservation) == 0) {
                    header('HTTP/1.1 404 Not Found');
                    echo "404 Not Found";
                    return;
                }
                $first_name = $reservation['RESERVATION_FIRSTNAME'];
                $last_name = $reservation['RESERVATION_LASTNAME'];
                $train_id = $reservation['TRAIN_ID'];
                $train = $db->select("SELECT * FROM TRAINS WHERE TRAIN_ID = $train_id")[0];
                $reservation['TRAIN'] = $train;
                $reservation['RESERVATION_FIRSTNAME'] = $first_name;
                $reservation['RESERVATION_LASTNAME'] = $last_name;
                unset($reservation['TRAIN_ID']);
                header('HTTP/1.1 200 OK');
                header('Content-Type: application/json');
                echo json_encode($reservation);
            }
        }elseif (count($args) == 0) {
            $db = new Database();
            $reservation = $db->select("SELECT * FROM RESERVATIONS");
            $reservations = array();
            foreach ($reservation as $key => $value) {
                $first_name = $value['RESERVATION_FIRSTNAME'];
                $last_name = $value['RESERVATION_LASTNAME'];
                $train_id = $value['TRAIN_ID'];
                $train = $db->select("SELECT * FROM TRAINS WHERE TRAIN_ID = $train_id")[0];
                $reservation[$key]['TRAIN'] = $train;
                $reservation[$key]['RESERVATION_FIRSTNAME'] = $first_name;
                $reservation[$key]['RESERVATION_LASTNAME'] = $last_name;
                unset($reservation[$key]['TRAIN_ID']);
                $reservations[] = $reservation[$key];
            }
            header('HTTP/1.1 200 OK');
            header('Content-Type: application/json');
            echo json_encode($reservations);
        }
    }

    function delete_reservation($args) {
        if (count($args) != 0) {
            header('HTTP/1.1 412 Precondition Failed');
            echo "412 Precondition Failed";
        }
        $json = file_get_contents('php://input');
        $data = json_decode($json, true);
        if (!isset($data['reservation_id'])) {
            header('HTTP/1.1 412 Precondition Failed');
            echo "412 Precondition Failed";
        }
        $reservation_id = $data['reservation_id'];
        if (!is_numeric($reservation_id)) {
            header('HTTP/1.1 412 Precondition Failed');
            echo "412 Precondition Failed";
        }
        $db = new Database();
        if (count($db->select("SELECT * FROM RESERVATIONS WHERE RESERVATION_ID = $reservation_id")) == 0) {
            header('HTTP/1.1 404 Not Found');
            echo "404 Not Found";
            return;
        }
        $db->execute("DELETE FROM RESERVATIONS WHERE RESERVATION_ID = $reservation_id");
        header('HTTP/1.1 200 OK');
        echo "200 OK";
    }
?>