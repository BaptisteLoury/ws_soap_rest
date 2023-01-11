<?php
    require_once 'controllers/db.php';

    function get_trains($condition) {
        $query = "SELECT * FROM TRAINS WHERE 1 ";
        if ($condition != null) {
            $query .= "AND $condition";
        }
        $db = new Database();
        $trains = $db->select($query);
        return $trains;
    }

    function get_train_info($args) {
        var_dump($args);
        if (count($args) == 1) {
            if (is_numeric($args[0])) {
                $id = $args[0];
                $train = get_trains("TRAIN_ID = $id");
                echo json_encode($train[0]);
            }
        }elseif (count($args) == 0) {
            $train = get_trains(null);
            echo json_encode($train);
        }
    }

    function get_train_info_destination($args){
        $db = new Database();
        $c = $db->select("SELECT DISTINCT TRAIN_SOURCE FROM TRAINS UNION SELECT DISTINCT TRAIN_DESTINATION FROM TRAINS");
        $citys = array();
        foreach ($c as $value) {
            $citys[] = $value['TRAIN_SOURCE'];
        }
        if (count($args) != 4) {
            echo "{\"status\": \"0\", \"message\": \"Invalid number of arguments\"}";
        }
        if ($args[0] == 'from' && $args[2] == 'to') {
            if (in_array($args[1], $citys) && in_array($args[3], $citys)) {
                $source = $args[1];
                $destination = $args[3];
                $train = get_trains("TRAIN_SOURCE = '$source' AND TRAIN_DESTINATION = '$destination'");
                echo json_encode($train);
            }else{
                echo "{\"status\": \"0\", \"message\": \"Invalid city name(s)\"}";
            }
        }else{
            echo "{\"status\": \"0\", \"message\": \"Invalid arguments\"}";
        }
    }
?>