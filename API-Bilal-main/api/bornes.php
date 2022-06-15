<?php
$utilisateur = 'bornegel';
$mdp = 'bornegel';
$db = "mysql:dbname=borne_gel;host=51.210.151.13:3306";

try {
    $pdo = new PDO($db, $utilisateur, $mdp); // test connexion
} catch (Exception $e) {
    $response = array("erreur" => 'Echec de la connexion à la base de données'); // connexion non établie
}

$pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);//On veux un tableau associati
$query = "SELECT idBorne,niveauGEL,niveauBAT,nomsalle,nom FROM  borne_gel.borne INNER JOIN salle ON borne_gel.borne.borne_idSalle = borne_gel.salle.idSalle INNER JOIN borne_gel.etablissement ON borne_gel.salle.salle_idEtab = borne_gel.etablissement.idEtab";
$stmt = $pdo->prepare($query);
$rslt = $stmt->execute();

if ($rslt == false) {
    $response = array("erreur" => "echec de la requête");
} else {
    // var_dump($stmt->fetchAll());
    $tab = $stmt->fetchAll();
    $response = array();
    foreach ($tab as $n) {
        $response[] = array("id" => $n['idBorne'], "nivGel" => $n['niveauGEL'], "nivBat" => $n['niveauBAT'], "salle" => $n['nomsalle'], "etablissement" => $n['nom']); //Création d'un tableau qui sera le résultat
    }
}

echo json_encode($response);
