/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.lurcat.ppe3.Interface;

import dev.lurcat.ppe3.Class.BaseDeDonnee.DaoSIO;
import dev.lurcat.ppe3.Class.BaseDeDonnee.Bdd;
import dev.lurcat.ppe3.Class.Client;
import dev.lurcat.ppe3.Class.PDFgenerator;
import java.awt.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author a.masvidal
 */
public class Panier extends javax.swing.JFrame {

    private Catalogue parent = null;
    private Bdd bdd = new Bdd();
    private ArrayList<String> infoPanier = new ArrayList<String>();

    /**
     * Creates new form Panier
     */
    public Panier() {
        initComponents();
    }

    /**
     * Initialise l'icon de la page et du boutton supprimer
     * valorise la propriété parent avec la réference de la class Catalogue
     * Si le client est définit valorise le jLabelClient avec le nom et prenom du client
     * @param pParent
     * @throws SQLException 
     */
    public Panier(Catalogue pParent) throws SQLException {
        ImageIcon img = new ImageIcon("F:\\DEV\\2TAK Hardware\\www\\Icones\\icons8-processeur-64.png");
        this.setIconImage(img.getImage());
        
        initComponents();
        
        this.parent = pParent;
        InitTableauPanier();
        
        jButtonSpprimer.setIcon(new ImageIcon(new ImageIcon("Icones/icons8-supprimer-pour-toujours-64.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
    
        setLabelClient();
    }


    public void setLabelClient(){
        if (parent.getLeClient() != null) {
            jLabelClient.setText(parent.getLeClient().getNom()+" "+parent.getLeClient().getPrenom());
        }
    }
    
    /**
     * Initialise le tableau du panier avec les informations du panier et remplie un ArrayList de ces informations pour la génération du PDF
     * Définit le text du jLabelPrixTotale avec la somme de toutes les valeurs du tableau
     * @throws SQLException 
     */
    public void InitTableauPanier() throws SQLException {
        DefaultTableModel leModel = (DefaultTableModel) jTablePanier.getModel();
        leModel.setRowCount(0);
        infoPanier.clear();
        
        jTablePanier.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel model = (DefaultTableModel) jTablePanier.getModel();
        Float prixTotale = 0f;
        
        for (Integer key : this.parent.getPanier().keySet()) {
            ResultSet infoProduit = DaoSIO.getInstance().requeteSelection("SELECT libelle, tarif from produit WHERE Id_Produit = " + key);

            while (infoProduit.next()) {
                prixTotale += parent.getPanier().get(key) * Float.parseFloat(infoProduit.getString("tarif"));
                model.addRow(new Object[]{key,infoProduit.getString("libelle"), parent.getPanier().get(key), Float.parseFloat(infoProduit.getString("tarif")), parent.getPanier().get(key) * Float.parseFloat(infoProduit.getString("tarif")), null});
                infoPanier.add(String.valueOf(key));
                infoPanier.add(infoProduit.getString("libelle"));
                infoPanier.add(String.valueOf(parent.getPanier().get(key)));
                infoPanier.add(String.valueOf(Float.parseFloat(infoProduit.getString("tarif"))));
                infoPanier.add(String.valueOf(parent.getPanier().get(key) * Float.parseFloat(infoProduit.getString("tarif"))));
            }
        }
        jLabelPrixTotale.setText("Prix Totale : "+String.valueOf(prixTotale)+"€");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLibelle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePanier = new javax.swing.JTable();
        jButtonSpprimer = new javax.swing.JButton();
        jButtonAjouter = new javax.swing.JButton();
        jButtonDiminuer = new javax.swing.JButton();
        jButtonCatalogue = new javax.swing.JButton();
        jButtonValider = new javax.swing.JButton();
        jLabelPrixTotale = new javax.swing.JLabel();
        jLabelClient = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("2TAK HARDWARE | Panier");

        jLabelLibelle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelLibelle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLibelle.setText("Panier");

        jTablePanier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Produit", "Produit", "Quantité", "PrixUnitaireTTC", "PrixTotale"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePanier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTablePanierKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePanier);
        if (jTablePanier.getColumnModel().getColumnCount() > 0) {
            jTablePanier.getColumnModel().getColumn(0).setResizable(false);
            jTablePanier.getColumnModel().getColumn(1).setResizable(false);
            jTablePanier.getColumnModel().getColumn(2).setResizable(false);
            jTablePanier.getColumnModel().getColumn(3).setResizable(false);
        }

        jButtonSpprimer.setText("Supprimer");
        jButtonSpprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSpprimerMouseClicked(evt);
            }
        });

        jButtonAjouter.setText("+");
        jButtonAjouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAjouterMouseClicked(evt);
            }
        });

        jButtonDiminuer.setText("-");
        jButtonDiminuer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDiminuerMouseClicked(evt);
            }
        });

        jButtonCatalogue.setText("Catalogue");
        jButtonCatalogue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCatalogueMouseClicked(evt);
            }
        });

        jButtonValider.setText("Valider");
        jButtonValider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonValiderMouseClicked(evt);
            }
        });

        jLabelPrixTotale.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPrixTotale.setText("PrixTotale : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLibelle, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSpprimer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAjouter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDiminuer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelPrixTotale, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCatalogue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonValider))
                    .addComponent(jLabelClient, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelClient, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSpprimer)
                            .addComponent(jButtonAjouter)
                            .addComponent(jButtonDiminuer)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelPrixTotale)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCatalogue)
                    .addComponent(jButtonValider))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTablePanierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePanierKeyPressed
        if (jTablePanier.getSelectedColumn() != -1 && jTablePanier.getSelectedRow() != -1) {

        }
    }//GEN-LAST:event_jTablePanierKeyPressed

    /**
     * Si le client est définit insère la commande dans la BDD et si l'insertion retourne un idCommande un pdf est généré
     * @param evt 
     */
    private void jButtonValiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonValiderMouseClicked
        if (this.parent.getLeClient() != null) {
            int i = JOptionPane.showConfirmDialog(this, "Voulez-vous confirmer cette vente");
            if (i == 0) {
                try {
                    Integer idCommande = bdd.InsererCommande(parent.getPanier(), parent.getLeClient().getId(), parent.getId_Personnel());
                    
                    if(idCommande != 0){
                        PDFgenerator generateur = new PDFgenerator();
                        generateur.generePDF(infoPanier, jLabelPrixTotale.getText(),idCommande, parent.getLeClient());
                    }
                } catch (Exception e) {
                }
            } else {
            }
        }else{
            ClientForm unClientForm = new ClientForm(this.parent);
            unClientForm.setVisible(true);
            unClientForm.setLocationRelativeTo(this);
        }
    }//GEN-LAST:event_jButtonValiderMouseClicked

    /**
     * Si un élément du tableau est séléctionné appel la méthode addProduitPanier de la class Catalogue pour incrementer un produit du panier
     * @param evt 
     */
    private void jButtonAjouterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAjouterMouseClicked
        if (jTablePanier.getSelectedRow() != -1) {
            Object objIdProduit = jTablePanier.getValueAt(jTablePanier.getSelectedRow(), 0);
            if (objIdProduit instanceof Integer) {
                try {
                    Integer idProduit = ((Integer) objIdProduit);
                    Integer stockProduit = bdd.getStockProduit(idProduit);
                    parent.addProduitPanier(this, idProduit, stockProduit);
                    InitTableauPanier();
                } catch (SQLException ex) {
                    Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vueillez selectionner un produit.");
        }
    }//GEN-LAST:event_jButtonAjouterMouseClicked

    /**
     * Si un élément du tableau est séléctionné appel la méthode decrementerProduitPanier de la class Catalogue pour decrementer un produit du panier
     * @param evt 
     */
    private void jButtonDiminuerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDiminuerMouseClicked
        if (jTablePanier.getSelectedRow() != -1) {
            Object objIdProduit = jTablePanier.getValueAt(jTablePanier.getSelectedRow(), 0);
            if (objIdProduit instanceof Integer) {
                try {
                    Integer idProduit = ((Integer) objIdProduit);
                    parent.decrementerProduitPanier(this, idProduit);
                    InitTableauPanier();
                } catch (SQLException ex) {
                    Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vueillez selectionner un produit.");
        }
    }//GEN-LAST:event_jButtonDiminuerMouseClicked

    /**
     * Si un élément du tableau est séléctionné appel la méthode supprimerProduitPanier de la class Catalogue pour supprimer un produit du panier
     * @param evt 
     */
    private void jButtonSpprimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSpprimerMouseClicked
        if (jTablePanier.getSelectedRow() != -1) {
            Object objIdProduit = jTablePanier.getValueAt(jTablePanier.getSelectedRow(), 0);
            if (objIdProduit instanceof Integer) {
                try {
                    Integer idProduit = ((Integer) objIdProduit);
                    parent.supprimerProduitPanier(this, idProduit);
                    InitTableauPanier();
                } catch (SQLException ex) {
                    Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vueillez selectionner un produit.");
        }
    }//GEN-LAST:event_jButtonSpprimerMouseClicked

    private void jButtonCatalogueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCatalogueMouseClicked
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCatalogueMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Panier().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonCatalogue;
    private javax.swing.JButton jButtonDiminuer;
    private javax.swing.JButton jButtonSpprimer;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JLabel jLabelClient;
    private javax.swing.JLabel jLabelLibelle;
    private javax.swing.JLabel jLabelPrixTotale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePanier;
    // End of variables declaration//GEN-END:variables
}
