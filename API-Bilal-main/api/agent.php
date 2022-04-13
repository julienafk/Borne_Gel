<?php
$utilisateur = 'bornegel';
$mdp = 'bornegel';
$db = "mysql:dbname=borne_gel;host=51.210.151.13:3306";

try {
    $pdo = new PDO($db, $utilisateur, $mdp); // test connexion
} catch (Exception $e) {
    $response = array("erreur" => 'Echec de la connexion à la base de données'); // connexion non établie
}


if (isset($_GET['idAgent'])) {
    $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);//On veut un tableau associatif
    $query = "SELECT idCompte,prenom,idBorne FROM borne_gel.compte INNER JOIN borne_gel.borne WHERE idCompte = borne_idCompte and idCompte = :id";
    $stmt = $pdo->prepare($query);
    $stmt->bindValue(':id', $_GET['idAgent']);
    $rslt = $stmt->execute();

    if ($rslt == false) {
        $response = array("erreur" => "echec de la requête");
    } else {
        $tab = $stmt->fetchAll();
//        var_dump($tab);
        $response = array("id" => $tab[0]['idCompte'], "agent" => $tab[0]['prenom']);
        foreach ($tab as $n) {
             //Création d'un tableau qui sera le résultat
            $response['bornes'][]= $n['idBorne'];
        }
    }
} else {
    $response = array("erreur" => "Renseigner les parametres");
}

echo json_encode($response);
