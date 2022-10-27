<?php
    require_once 'controllers/db.php';

    function get_trains($num) {
        if ($num == -1){
            $query = "SELECT * FROM trains";
        } else {
            $query = "SELECT * FROM trains WHERE train_id = $num";
        }
        $db = new Database();
        $trains = $db->select($query);
        return $trains;
    }

    function get_train_info($args) {
        if (count($args) == 2) {
            if (is_numeric($args[1])) {
                $train = get_trains($args[1]);
                echo json_encode($train[0]);
            }
        }elseif (count($args) == 1) {
            $train = get_trains(-1);
            echo json_encode($train);
        }
    }

?>