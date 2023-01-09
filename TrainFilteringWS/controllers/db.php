<?php
    class Database{
        private $co;
        private $host = 'mysql';
        private $user = 'root';
        private $pass = 'root';
        private $db = 'train-filtering';
        private $port = 3306;

        public function __construct() {
            $this->co = new mysqli($this->host, $this->user, $this->pass, $this->db, $this->port);
            if ($this->co->connect_error) {
                die("Connection failed: " . $this->co->connect_error);
            }
        }

        public function select($query) {
            $result = $this->co->query($query);
            $data = array();
            if ($result->num_rows > 0) {
                while($row = $result->fetch_assoc()) {
                    $data[] = $row;
                }
            }
            return $data;
        }

        public function execute($query) {
            $result = $this->co->query($query);
            return $result;
        }

        public function __destruct() {
            $this->co->close();
        }
    }
?>