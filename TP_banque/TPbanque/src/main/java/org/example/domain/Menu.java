package org.example.domain;

import org.example.entity.Agence;
import org.example.entity.Client;
import org.example.entity.Compte;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    // creation de mon EnityManager
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_tp1");

    private static EntityManager em = emf.createEntityManager();

    // preparation d'un affichage pour mon menu
    public static void aff_menu(){
        System.out.println("1 - Créer tout");
        System.out.println("2 - Créer un compte");
        System.out.println("3 - Ajouter un client à un compte");
        System.out.println("4 - Afficher les informations");
        System.out.println("5 - Exit");
    }


    // methode static qui va me permettre de lancer mon menu
    public static void exo() {
        System.out.println("######## TP Banque ######## ");
     //   em.getTransaction().begin();
        Scanner sc = new Scanner(System.in);
        int choix =0;
        do{
            try{
              //  afficheAll();
                aff_menu();  // utilisation de l'affichage pour mon menu
                choix = sc.nextInt();
                switch (choix){
                    case 1 :
                        creation();  // methode pour creer une agence un compte et un client
                        break;
                    case 2 :
                        create_compte(); // methode pour creer un compte (dans une agence existante et pour un client existant)
                        break;
                    case 3 :
                        addClient(); // rajout d'un proprietaire (client existant) pour un compte existant
                        break;
                    case 4 :
                       // em.getTransaction().commit();
                        afficheAll();  // affichage des informations
                       // em.getTransaction().begin();
                        break;
                    case 5 :
                        System.out.println("Aurevoir");
                      //  em.getTransaction().commit();
                        em.close();
                        emf.close();
                        break;
                    default:
                        System.out.println("Commande invalide réessayer"); // si on ne rentre dans aucune case
                        break;
                }

            }catch (InputMismatchException ex){  // prise en compte si l'utilsateur tape autre chose qu'un entier
                System.out.println("Veuillez entrer un entier");
                aff_menu();
            }

        }while(choix != 5); // Pour quitter mon menu l'utilisateur doit taper 5



    }

    public static void create_compte(){
        em.getTransaction().begin();  // debut de ma transaction
        // recuperation des informations ( client et agence par leur id)
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre l'id du client : ");
        Long id_c = sc.nextLong();
        Client client = em.find(Client.class,id_c);
        System.out.println("Entre l'id de l'agence : ");
        Long id_a = sc.nextLong();
        Agence agence = em.find(Agence.class,id_a);
        // debut creation d'un nouveau compte
        System.out.println("Entrer le libellé du compte : ");
        String libelle = sc.next();
        System.out.println("Entre l'IBAN :");
        String iban = sc.next();
        System.out.println("Entrer le solde du compte : ");
        double solde = sc.nextDouble();
        Compte compte = new Compte();
        compte.setLibelle(libelle);
        compte.setIban(iban);
        compte.setSolde(solde);
        // ajout de ce compte a l'agence selectionner et ajout du client selectionner comme titulaire du compte
        compte.setAgence(agence);
        List<Client> listcl = new ArrayList<>();
        listcl.add(client);
        compte.setClients(listcl);
      //  compte.getClients().add(client);
      //  client.getComptes().add(compte);
        List<Compte> malistcmp = client.getComptes();
        malistcmp.add(compte);
        client.setComptes(malistcmp);
        em.persist(compte);
        // commit de ma transaction
        em.getTransaction().commit();
    }

    public static void addClient(){
        em.getTransaction().begin(); // debut de ma transaction
        // recuperation du compte et du client par leurs id
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre l'id du compte : ");
        Long id_a = sc.nextLong();
        Compte compte = em.find(Compte.class,id_a);
        System.out.println("Entre l'id du client : ");
        Long id_b = sc.nextLong();
        Client client = em.find(Client.class,id_b);
        // ajout du client selectionner a la liste des titulaires du compte
        List<Client> newliste = compte.getClients();
        newliste.add(client);
        compte.setClients(newliste);
        em.persist(compte);
        List<Compte> comptes = client.getComptes();
        comptes.add(compte);
        client.setComptes(comptes);
        em.persist(client);
        // commit de ma transaction
        em.getTransaction().commit();
    }

    public static void afficheAll(){
        em.getTransaction().begin(); // debut de ma transaction
        System.out.println("##############  Affichage informations ############## ");
        // requete pour recuperer la liste des agences
        Query query= em.createQuery("select a from Agence a");
        List<Agence> agences = query.getResultList();
        // parcours de la liste des agences pour afficher les informations
        for(Agence a : agences){
            System.out.println("######################");
            System.out.println("Agence avec l'id : "+a.getId()+" a l'adresse : "+a.getAdresse());
            for(Compte c : a.getComptes()){
                System.out.println("\tCompte avec l'id : "+c.getId()+" libelle : "+c.getLibelle()+" solde : "+c.getSolde());
                System.out.println("\t\tProprietaire(s) du compte : ");
                for(Client cl : c.getClients()){
                    System.out.println("\t\tClient avec l'id : "+cl.getId()+" nom : "+cl.getNom()+" prenom : "+cl.getPrenom());
                }
            }
            System.out.println("######################");
        }
        em.getTransaction().commit(); // commit de ma transaction
    }

    public static void creation(){
        em.getTransaction().begin(); // debut transaction
        Scanner sc = new Scanner(System.in);
        // creation de mon agence
        System.out.println("Entrer l'adresse de l'agence");
        String adresse = sc.nextLine();
        Agence agence = new Agence();
        agence.setAdresse(adresse);
        em.persist(agence);
        // creation de mon client
        Client client = new Client();
        System.out.println("Entrer le nom");
        String nom = sc.next();
        System.out.println("Entrer le prenom");
        String prenom = sc.next();
        System.out.println("Entrer la date de naissance (format YYYY-MM-DD)");
        String date_s = sc.next();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(date_s);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setNaissance_d(date);
        em.persist(client);
        // creation de mon compte
        System.out.println("Entrer le libellé du compte : ");
        String libelle = sc.next();
        System.out.println("Entrer l'IBAN");
        String iban = sc.next();
        System.out.println("Entrer le solde du compte : ");
        double solde = sc.nextDouble();
        Compte compte = new Compte();
        compte.setLibelle(libelle);
        compte.setIban(iban);
        compte.setSolde(solde);
        // Relation entre mon agence le compte et le client cree
        compte.setAgence(agence);
        List<Client> malistedecient = new ArrayList<>();
        malistedecient.add(client);
       // compte.getClients().add(client);
        compte.setClients(malistedecient);
        List<Compte> malistedecompte = new ArrayList<>();
        malistedecompte.add(compte);
        client.setComptes(malistedecompte);
       // client.getComptes().add(compte);
        em.persist(compte);
        List<Compte> listecmpagence = new ArrayList<>();
        listecmpagence.add(compte);
        agence.setComptes(listecmpagence);
        em.persist(agence);
        em.getTransaction().commit(); // commit de ma transaction
    }
}
