<?php
    require_once 'controllers/db.php';

    function get_trains($condition) {
        $query = "SELECT * FROM TRAINS WHERE 1 ";
        if ($condition != "") {
            $query .= "$condition ";
        }
        $db = new Database();
        $trains = $db->select($query);

        return $trains;
    }

    function get_train_info($args) {
        if (count($args) == 1) {
            if (is_numeric($args[0])) {
                $id = $args[0];
                $train = get_trains("AND TRAIN_ID = $id");
                echo json_encode($train[0]);
            }
        }elseif (count($args) == 0) {
            $train = get_trains(null);
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
            echo "412 Precondition Failed";
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
                echo "412 Precondition Failed";
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
                echo json_encode($train);
            }else{
                header('HTTP/1.1 412 Precondition Failed');
                echo "412 Precondition Failed";
            }
        }else{
            header('HTTP/1.1 412 Precondition Failed');
            echo "412 Precondition Failed";
        }
    }
?>