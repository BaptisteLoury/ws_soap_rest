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
        // echo "get_train_info called\n args: ";
        $trains = get_trains(100);
        $train = $trains[$args[1]];
        echo json_encode($train);
    }

    function get_trains_info($args){
        // echo "get_trains_info called\n args: ";
        // var_dump($args);
        if (count($args) == 0) {
            // get all trains
            echo json_encode(get_trains(-1));
        } else {
            // get number of trains specified by $args[0]
            echo json_encode(get_trains($args[0]));
        }
    }
?>