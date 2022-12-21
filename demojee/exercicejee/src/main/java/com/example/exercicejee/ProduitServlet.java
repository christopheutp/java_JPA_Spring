package com.example.exercicejee;

import com.example.exercicejee.entities.Produit;
import com.example.exercicejee.services.ProduitService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "produitServlet", value = "/produits")
public class ProduitServlet extends HttpServlet {

    private List<Produit> produits;

    private ProduitService produitService;

    public void init(){
        produitService = new ProduitService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        produits = produitService.findAll();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");

        if(request.getParameter("id") != null){
            int id = Integer.parseInt(request.getParameter(("id")));
            Produit produit = new Produit();
            try {
                produit = produitService.findById(id);
            }catch (Exception e){

            }
            if(produit != null) {
                writer.println("<div>");
                writer.println(produit.getMarque()+" "+produit.getReference());
                writer.println("</div>");
            }else {
                writer.println("<div>");
                writer.println("aucun produit avec cet id");
                writer.println("</div>");
            }
        }else{
            for(Produit p : produits){
                writer.println("<div>");
                writer.println(p.getId()+" "+p.getReference());
                writer.println("</div>");
            }
        }

        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");
        if(request.getParameter("marque") != null && request.getParameter("prix") != null){
            String marque = request.getParameter("marque");
            double prix = Double.parseDouble(request.getParameter("prix"));
            String reference = request.getParameter("reference");
            int stock = Integer.parseInt(request.getParameter("stock"));
            Produit produit = new Produit(marque,reference,prix,stock);
            if(produitService.create(produit)){
                writer.println("<div>Produit ajoutée "+produit.getId()+"</div>");
            }else{
                writer.println("<div>erreur d'ajout </div>");
            }
        }else {
            writer.println("<div>erreur parametres</div>");
        }

        writer.println("</body></html>");
    }
}
