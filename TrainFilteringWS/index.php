<?php
    ini_set('display_errors', 1);
    ini_set('display_startup_errors', 1);
    error_reporting(E_ALL);


    require_once 'controllers/Trains.php';
    require_once 'routing/Route.php';

    echo "request method: " . $_SERVER['REQUEST_METHOD'] . "\n";
    
    echo "request uri: " . $_SERVER['REQUEST_URI'] . "\n";

    $route = new Route();

    $route->add('/trains/[0-9][0-9]*', 'get_train_info', 'GET');
    $route->submit();
    var_dump(preg_match('/^\/trains\/[0-9][0-9]*$/', '/trains/1'));

?>