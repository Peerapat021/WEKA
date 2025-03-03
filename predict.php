<?php
// Set error reporting for debugging
ini_set('display_errors', 1);
error_reporting(E_ALL);

// Check if form was submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get form data
    $vendor = $_POST["vendor"];
    $myct = $_POST["myct"];
    $mmin = $_POST["mmin"];
    $mmax = $_POST["mmax"];
    $cach = $_POST["cach"];
    $chmin = $_POST["chmin"];
    $chmax = $_POST["chmax"];

    // Path to the Java class and model - use directory relative to this file
    $baseDir = dirname(__FILE__);
    $javaClassPath = $baseDir . "/weka.jar;" . $baseDir;
    $modelPath = $baseDir . "/Trees.model";

    // Build the command to execute Java
    $command = "java -cp \"$javaClassPath\" WekaPredictor \"$modelPath\" \"$vendor\" $myct $mmin $mmax $cach $chmin $chmax 2>&1";

    // Execute the command
    $output = shell_exec($command);

    // Trim the output to remove any whitespace
    $output = trim($output);

    // Return the prediction
    if (!empty($output)) {
        echo json_encode(["status" => "success", "prediction" => $output]);
    } else {
        echo json_encode(["status" => "error", "message" => "No prediction returned"]);
    }
} else {
    // If not POST request, return error
    echo json_encode(["status" => "error", "message" => "Invalid request method"]);
}
