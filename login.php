<?php 
	//Get input data and store in variable named $data
	$data = file_get_contents('php://input');

    //Decode the data because it came from android app in json format
    //The data will be stored in $array after decoding
	$array = json_decode($data, true);

    //We take username and password from $array and store them in variables
	$username =  $array["username"];
	$password =  $array["password"];
	
	//We create an empty array named $result
	$result = array();

    //We check if username and password came from the android app match
    //our username(safeer) and password(safeer) here.
	if ($username == "safeer" && $password == "safeer")
	{
	    //If match is successful, we add to $result array success=1
        $result["success"] = 1;
	}
	else
	{
	    //If match is failed, we add to $result array success=0
	    //and we also add the reason to $result  array
    	$result['success'] = 0;
    	$result['reason'] = "username/password was incorect";
	}
    
    //We encode the $result array to json because it must go to android app in json format
	echo json_encode($result);
?>