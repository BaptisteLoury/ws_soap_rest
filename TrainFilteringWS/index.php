<?php
    ini_set('display_errors', 1);
    ini_set('display_startup_errors', 1);
    error_reporting(E_ALL);
    
    // $_SERVER['rootdir'] = '/ws_soap_rest/TrainFilteringWS';

    require_once 'controllers/Trains.php';
    require_once 'routing/Route.php';

    // echo "request method: " . $_SERVER['REQUEST_METHOD'] . "\n";
    // echo "request uri: " . $_SERVER['REQUEST_URI'] . "\n";

    $route = new Route('/ws_soap_rest/TrainFilteringWS');

    $route->add('/trains/info/[0-9][0-9]*', 'get_train_info', 'GET');
    $route->add('/trains/info', 'get_train_info', 'GET');
    


    $route->submit();

?>