<?php

class ModelApp{
    private $connection;

    public function __construct(){
        $host     = "localhost";
		$user     = "root";
		$password = "";
        $database = "APP";
        $this->connection  =mysqli_connect($host,$user,$password,$database); 
    }


    public function login_user($user, $pass){
        $arr = null;
        $query = "SELECT * FROM usuario WHERE idUsuario = '" . $user . "'AND contrasena = '" . $pass . "'";
        mysqli_set_charset($this->connection, 'utf8');
        $sql = mysqli_query($this->connection, $query);

        if(mysqli_fetch_assoc($sql)){
            $row =  mysqli_fetch_object($sql) ;
            $arr = array('name' => $row->nombre, 'lastName' => $row->apellidoP, 'lastNameM' => $row->apellidoM, 'email' => $row->correo, 'username' => $row->idUsuario, 'loginApproval' => 1);
            
        }else{
            $arr = array('loginApproval' => 0);
        
        }
        header("Content-Type: application/json");
        echo json_encode($arr);
        exit();
        
    }

    public function register_user($username, $name, $lastName, $lastNameM, $email, $pass){
        $arr = null;
        $query = "INSERT INTO Usuario VALUES ('" . $username . "','" . $name . "','" . $lastName . "','" . $lastNameM . "','" . $email . "','" .$pass . "')";
        mysqli_set_charset($this->connection, 'utf8');
        $sql = mysqli_query($this->connection, $query);

        if(mysqli_affected_rows($this->connection) > 0 ){
            $arr = array('registerApproval' => 1);
        }else{
            $arr = array('registerApproval' => 0, 'error' => 'Usuario duplicado'); 
        }

        header("Content-Type: application/json");
        echo json_encode($arr);
        exit();
    }
}
?>