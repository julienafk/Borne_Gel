<?php
$utilisateur = 'bornegel';
$mdp = 'bornegel';
$db = "mysql:dbname=borne_gel;host=51.210.151.13:3306";

try {
    $pdo = new PDO($db, $utilisateur, $mdp); // test connexion
} catch (Exception $e) {
    $response = array("erreur" => 'Echec de la connexion à la base de données'); // connexion non établie
}

if (isset($_GET['id']) && isset($_GET['nivGel']) && isset($_GET['nivBat'])) {
    $query = "UPDATE borne_gel.borne SET niveauGEL=:gel, niveauBAT=:bat WHERE idBorne=:id";
    $stmt = $pdo->prepare($query);
    $stmt->bindValue(':gel', $_GET['nivGel']);
    $stmt->bindValue(':bat', $_GET['nivBat']);
    $stmt->bindValue(':id', $_GET['id']);
    $rslt = $stmt->execute();

    // var_dump($stmt->errorInfo());

    if ($rslt == false) {
        $response = array("erreur" => "echec de la requête");
    } else {
        $response = array("id" => $_GET['id'], "nivGel" => $_GET['nivGel'], "nivBat" => $_GET['nivBat']); //Création d'un tableau qui sera le résultat
    }
} else {
    $response = array("erreur" => "Renseigner les parametres");
}
echo json_encode($response);
