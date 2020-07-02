<?php
require_once('koneksinative.php');
$base64_string=$_POST['image'];
$username = $_POST['username'];
$image_name = $username.".png";
  
 if(validateString($base64_string))
 {
 $data = explode(',', $base64_string);
 if(!file_exists($_SERVER["DOCUMENT_ROOT"] . "/kantin/assets/". $username)){
	mkdir($_SERVER["DOCUMENT_ROOT"] . "/kantin/assets/". $username);
	$ifp = fopen($_SERVER['DOCUMENT_ROOT'] ."/kantin/assets/".$username."/".$image_name, "wb");// use your folder path
 }else{
	$ifp = fopen($_SERVER['DOCUMENT_ROOT'] ."/kantin/assets/".$username."/".$image_name, "wb");// use your folder path
 }
//  $ifp = fopen($_SERVER['DOCUMENT_ROOT'] ."/kantin/assets/".$image_name, "wb");// use your folder path
 fwrite($ifp, base64_decode($data[1]));
 fclose($ifp);
  
//inserting the picture name to your table , here i am just inserting pic name, you can insert
//other details also
$con = new mysqli('', 'root', '', 'kantin2');
 $query = "UPDATE tb_driver SET foto = ? WHERE username = ?";
 $stmt = $con->prepare($query);
 $stmt->bind_param("ss", $image_name, $username);
 $stmt->execute();
 
 if($stmt->affected_rows==1){
  
 $final_arr=array();
 $final_arr["status"] = "200";
 $final_arr["msg"] = "successful";
 print_r(json_encode($final_arr));
  
 }else{
  
 $final_arr=array();
 $final_arr["status"] = "401";
 $final_arr["msg"] = "Not successful";
 print_r(json_encode($final_arr));
 }
 } 
 else
 {
  
 $final_arr=array();
 $final_arr["status"] = "501";
 $final_arr["msg"] = "Unsccessfull";
 print_r(json_encode($final_arr));
 
 }
  
//function to validate base64 string 
 function validateString($string)
 {
 $data = explode(',', $string);
 if(count($data) >= 2)
 {
 $format = explode('/', $data[0]);
 if($format[0] == 'data:image')
 {
 return true;
 }
 }
 return false;
 }
  
?>
