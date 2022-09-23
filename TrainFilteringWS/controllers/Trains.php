<?php
    function get_train_info($args){
        echo "get_train_info called\n args: ";
        var_dump($args);
        $trains = array();
        for ($i = 0; $i < 10; $i++) {
            $train = array(
                'id' => $i,
                'name' => 'train' . $i,
                'speed' => rand(0, 100),
                'direction' => rand(0, 1) == 0 ? 'north' : 'south'
            );
            array_push($trains, $train);
        }
        echo json_encode($trains);
    }
?>