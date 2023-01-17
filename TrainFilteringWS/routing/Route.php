<?php
    class Route {
        private $routes = array();
        private $rootdir = '';

        public function __construct($rootd) {
            $this->rootdir = $rootd;
        }

        public function add($uri, $function, $method) {
            $this->routes[] = array(
                'uri' => $this->rootdir . $uri,
                'function' => $function,
                'method' => $method
            );
        }

        public function submit() {
            $uri = $_SERVER['REQUEST_URI'];
            $method = $_SERVER['REQUEST_METHOD'];
            $servicecalled = str_replace($this->rootdir, '', $uri);
            $found = false;

            foreach ($this->routes as $route) {
                $reg = '/^' . str_replace('/', '\/', $route['uri']) . '$/';
                if (preg_match($reg, $uri)) {
                    $args =  array_slice(explode('/', $uri), 2 + substr_count(str_replace($servicecalled, '', $uri), '/'));
                    $found = true;
                    if ($route['method'] == $method) {
                        $function = $route['function'];
                        $function($args);
                    }
                }
            }
            
            if (!$found) {
                header('HTTP/1.1 405 Method Not Allowed');
                echo "405 Method not allowed";
            }
        }
    }
?>