<?php
    class Route {
        private $routes = array();
        
        public function add($uri, $function, $method) {
            $this->routes[] = array(
                'uri' => $uri,
                'function' => $function,
                'method' => $method
            );
        }

        public function submit() {
            $uri = $_SERVER['REQUEST_URI'];
            $method = $_SERVER['REQUEST_METHOD'];
            $servicecalled = str_replace($_SERVER['rootdir'], '', $uri);
            $found = false;
            // echo "service called: " . $servicecalled . "\n";

            foreach ($this->routes as $route) {
                // echo "uri: " . $uri . "\n";
                // var_dump($route);
                // echo "route uri: " . $route['uri'] . "\n";
                
                $reg = '/^' . str_replace('/', '\/', $route['uri']) . '$/';
                // echo "matching: " . preg_match($reg, $uri) . "\n";
                if (preg_match($reg, $uri)) {
                    $args =  array_slice(explode('/', $uri), 2 + substr_count(str_replace($servicecalled, '', $uri), '/'));
                    // echo "uri: ".  $uri . "\n";
                    // echo "args: ";
                    // var_dump($args);
                    $found = true;
                    // echo "comparing methods: " . $route['method'] . " == " . $method . "\n";
                    if ($route['method'] == $method) {
                        $function = $route['function'];
                        $function($args);
                    }
                }
            }
            if (!$found) {
                echo "404";
            }
        }

        public function get_routes() {
            return $this->routes;
        }

    }
?>