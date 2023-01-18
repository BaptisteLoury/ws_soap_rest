<?php
    // ini_set('display_errors', 1);
    // ini_set('display_startup_errors', 1);
    // error_reporting(E_ALL);
    
    // $_SERVER['rootdir'] = '/ws_soap_rest/TrainFilteringWS';

    require_once 'controllers/Trains.php';
    require_once 'routing/Route.php';

    $route = new Route('');

    $route->add('/trains/[0-9]+', 'get_train_info', 'GET');
    $route->add('/trains', 'get_train_info', 'GET');
    $route->add('/trains/from/[a-zA-Z]+/to/[a-zA-Z]+', 'get_train_info_destination', 'GET');
    $route->add('/trains/from/[a-zA-Z]+/to/[a-zA-Z]+/departure/[0-9]{4}(-[0-9]{2}){2}_[0-9]{2}:[0-9]{2}', 'get_train_info_destination', 'GET');
    $route->add('/trains/from/[a-zA-Z]+/to/[a-zA-Z]+/arrival/[0-9]{4}(-[0-9]{2}){2}_[0-9]{2}:[0-9]{2}', 'get_train_info_destination', 'GET');
    
    $route->add('/reservations', 'add_reservation_json', 'POST');
    $route->add('/reservations/[0-9]+', 'get_reservation', 'GET');
    $route->add('/reservations', 'get_reservation', 'GET');

    $route->add('/reservations', 'delete_reservation', 'DELETE');

    $route->submit();

?>