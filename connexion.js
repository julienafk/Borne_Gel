$(document).ready(function() {

    var url="../api/" //Début de l'URL

    $("#false").hide();//Cacher l'élément "false"

    $(".user").submit(function (e){ //Si un élément de la classe "User" est confirmé

        e.preventDefault(); //Empêcher l'envoi du formulaire

        if($("#exampleInputEmail")[0].value && $("#exampleInputPassword")[0].value){  //Si des valeurs sont présentes dans le formulaire

            localStorage.setItem('Email', $("#exampleInputEmail").val());//Ajouter le mail dans le LocalStorage pour le réutiliser sur d'autres pages

            var request = $.ajax({ //Envoyer une requête à l'Api
				
                method: "GET", //Méthode de la requête
                url: "http://51.210.151.13/btssnir/projets2022/bornegel/api/connexion.php", //Url vers l'API
				data: {email: $("#exampleInputEmail")[0].value,password:$("#exampleInputPassword")[0].value}, //Données transmises, ici le mail et le mot de passe
				dataType: "json", //Type de réponse attendu,ici du JSON 
                success: (response)=>{ //Si l'on reçoit une réponse à cette requête
				console.log(response); //Afficher le contenu de la requête
                }
            });

            request.done(function (msg){
                if(msg.success==true){
                    if(msg.grade==1){
                        window.location="./FournisseurSysteme.html";
                    }
                    else if(msg.grade==2){
                        window.location="./responsableTechnique.html";
                    }
                    else if(msg.grade==3){
                        window.location="./responsableAgent.html";
                    }
                    else if(msg.grade==4){
                        window.location="./agent.html";
                    }
                }
                else {
                    $("#false").text('Le compte inexistant');
                    $("#false").show();
                    setTimeout(() => { $("#false").hide(); }, 10000);
                }
            })
        }
        else{
            $("#false").text('Veuillez remplir tout les champs');
            $("#false").show();
            setTimeout(() => { $("#false").hide(); }, 10000);
        }
    })
    if(window.closed){  //si page est fermée
        localStorage.clear(); //supprime la page
    }
})






