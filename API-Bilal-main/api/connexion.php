<?php
$utilisateur = 'bornegel';
$mdp = 'bornegel';
$db = "mysql:dbname=borne_gel;host=51.210.151.13:3306";

try {
    $pdo = new PDO($db, $utilisateur, $mdp); // test connexion
} catch (Exception $e) {
    $response = array("erreur" => 'Echec de la connexion à la base de données'); // connexion non établie
}


if (isset($_GET['email']) && isset($_GET['password'])) {
    $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
    $query = "SELECT email,password FROM borne_gel.compte WHERE email=:email AND password=:pass";
    $stmt = $pdo->prepare($query);
    $stmt->bindValue(':email', $_GET['email']);
    $stmt->bindValue(':pass', $_GET['password']);
    $rslt = $stmt->execute();

    if ($rslt == false) {
        $response = array("erreur" => "echec de la requête");
    } else {
        $tab = $stmt->fetch();
        if (($tab != false) && ($tab['email'] == $_GET['email']) && ($tab['password'] == $_GET['password'])) {
            $response = array("identification" => true);
        } else {
            $response = array("identification" => false);
        }
    }
} else {
    $response = array("erreur" => "Renseigner les paramétres");
}

echo json_encode($response);
