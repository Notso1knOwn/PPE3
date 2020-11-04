/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reutilisable;

import dev.lurcat.ppe3.Class.BaseDeDonnee.DaoSIO;
import dev.lurcat.ppe3.Interface.Gestion;
import java.awt.Component;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author a.masvidal
 */
 public class gestionPanel extends javax.swing.JPanel {

    private String nomCategorie;
    private ArrayList<String> lesRequêtes = new ArrayList<>();
    private ResultSet lesInfo = null;
    private ResultSetMetaData lesInfoMD = null;

    /**
     * Creates new form gestionProfil
     */
    public gestionPanel() throws SQLException {
        initComponents();

        jPanelModif.setVisible(false);

//        nomCategorie = "Profil";
//
//        lesRequêtes.add("SELECT * FROM Profil ORDER BY Id_Profil");
//        lesRequêtes.add("INSERT INTO Profil(libelle, descriptif) VALUES (?,?)");
//        lesRequêtes.add("UPDATE Profil SET libelle = ? , descriptif = ? WHERE Id_Profil = ?");
//        lesRequêtes.add("DELETE FROM Profil WHERE Id_Profil = ?");

        nomCategorie = "Personnel";
        lesRequêtes.add("SELECT * FROM Personnel ORDER BY Id_Personnel");
       lesRequêtes.add("INSERT INTO Personnel VALUES (null,?,?,?,?,?,?,?)");
        lesRequêtes.add("UPDATE Personnel SET prenom = ? , nom = ? , pseudo = ? , mdp = ? , telephone = ? , email = ?, Id_Profil = ? WHERE Id_Personnel = ?");
        lesRequêtes.add("DELETE FROM Personnel WHERE Id_Personnel = ?");


//        InitTable();
//        InitPanelModif();
    }
    
    /**
     * 
     * @param pNomCategorie
     * @param pLesRequêtes
     * @throws SQLException 
     */
    public gestionPanel(String pNomCategorie, ArrayList<String> pLesRequêtes) throws SQLException {
        initComponents();
        
        this.nomCategorie = pNomCategorie;
        this.lesRequêtes = pLesRequêtes;
        
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPanelModif.setVisible(false);
        InitTable();
        InitPanelModif();
        jLabelTitleTable.setText("Liste des "+this.nomCategorie);
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public ArrayList<String> getLesRequêtes() {
        return lesRequêtes;
    }

    public void setLesRequêtes(ArrayList<String> lesRequêtes) {
        this.lesRequêtes = lesRequêtes;
    }
    
    
    
    public Integer getSelectedRow(){
        return jTable.getSelectedRow();
    }
    
    public ArrayList<String> getTableRowValues() throws SQLException{
        ArrayList<String> rowValues = new ArrayList<String>();
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            rowValues.add(String.valueOf(jTable.getValueAt(jTable.getSelectedRow(), i)));
        }
        return rowValues;
    }

    /**
     * Initialise le tableau de la section Profil avec les tuples contenu dans
     * la BDD

     * @throws SQLException
     */
    public void InitTable() throws SQLException {
        DefaultTableModel leModel = new DefaultTableModel();

        lesInfo = DaoSIO.getInstance().requeteSelection(lesRequêtes.get(0));
        lesInfoMD = lesInfo.getMetaData();
        
        for (int i = 1; i < lesInfoMD.getColumnCount() + 1; i++) {
            leModel.addColumn(lesInfoMD.getColumnName(i));
        }
        
        jTable.setModel(leModel);
        jTable.setDefaultEditor(jTable.getColumnClass(1), null);
        
        leModel = (DefaultTableModel) jTable.getModel();
        while (lesInfo.next()) {
            ArrayList<String> lesValeurs = new ArrayList<String>();
            for (int i = 1; i < lesInfoMD.getColumnCount()+1; i++) {
                lesValeurs.add(lesInfo.getString(i));
            }
            Vector vector = new Vector(lesValeurs);
            
            leModel.addRow(vector);
        }
    }
    
    /**
     * Initialise le panel jPanelModif avec autant de composant réutilisable TextFieldPanel que de colonne(attributs de la table) du table moins 1(l'Id)
     */
    public void InitPanelModif(){
        jPanelTextField.setLayout(new GridLayout(0,2));
        
        for (int i = 1; i < jTable.getColumnCount(); i++) {
            
               TextFieldPanel unPanel = new TextFieldPanel();
            unPanel.initText(jTable.getColumnName(i));
            unPanel.setName(jTable.getColumnName(i));
            jPanelTextField.add(unPanel); 
           
        }
        jPanelTextField.revalidate();
        jPanelTextField.repaint();
        
        jTable.setDefaultEditor(jTable.getColumnClass(1), null);
    }

    /**
     * Supprime toutes les lignes du tableau
     */
    public void clearTableProfil() {
        DefaultTableModel leModel = (DefaultTableModel) jTable.getModel();
        leModel.setRowCount(0);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitleTable = new javax.swing.JLabel();
        jScrollPaneTableProfil = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonCreer = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jPanelModif = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jButtonValider = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jPanelTextField = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(600, 500));

        jLabelTitleTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTitleTable.setText("Liste des Profils");

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable.setPreferredSize(new java.awt.Dimension(207, 158));
        jScrollPaneTableProfil.setViewportView(jTable);

        jButtonCreer.setText("Creer");
        jButtonCreer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCreerMouseClicked(evt);
            }
        });
        jButtonCreer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreerActionPerformed(evt);
            }
        });

        jButtonModifier.setText("Modifier");
        jButtonModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonModifierMouseClicked(evt);
            }
        });
        jButtonModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierActionPerformed(evt);
            }
        });

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSupprimerMouseClicked(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTitle.setText("jLabel2");

        jButtonValider.setText("Valider");
        jButtonValider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonValiderMouseClicked(evt);
            }
        });

        jButtonAnnuler.setText("Annuler");
        jButtonAnnuler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAnnulerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelTextFieldLayout = new javax.swing.GroupLayout(jPanelTextField);
        jPanelTextField.setLayout(jPanelTextFieldLayout);
        jPanelTextFieldLayout.setHorizontalGroup(
            jPanelTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelTextFieldLayout.setVerticalGroup(
            jPanelTextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelModifLayout = new javax.swing.GroupLayout(jPanelModif);
        jPanelModif.setLayout(jPanelModifLayout);
        jPanelModifLayout.setHorizontalGroup(
            jPanelModifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModifLayout.createSequentialGroup()
                .addGroup(jPanelModifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModifLayout.createSequentialGroup()
                        .addContainerGap(426, Short.MAX_VALUE)
                        .addComponent(jButtonAnnuler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonValider))
                    .addGroup(jPanelModifLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelTitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelModifLayout.createSequentialGroup()
                .addComponent(jPanelTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelModifLayout.setVerticalGroup(
            jPanelModifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModifLayout.createSequentialGroup()
                .addComponent(jLabelTitle)
                .addGap(14, 14, 14)
                .addComponent(jPanelTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelModifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValider)
                    .addComponent(jButtonAnnuler))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitleTable)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCreer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSupprimer)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelModif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneTableProfil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitleTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneTableProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCreer)
                    .addComponent(jButtonModifier)
                    .addComponent(jButtonSupprimer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelModif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCreerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCreerMouseClicked
        jLabelTitle.setText("Création d'un " + this.nomCategorie);
        jPanelModif.setName("Creer");
        jPanelModif.setVisible(true);
    }//GEN-LAST:event_jButtonCreerMouseClicked

    private void jButtonCreerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCreerActionPerformed

    /**
     * Si une ligne du tableau est selectionné set les valeurs des jTextField contenant dans les composants TextFieldPanel du panel jPanelModif
     * @param evt 
     */
    private void jButtonModifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModifierMouseClicked
        if (jTable.getSelectedRow() != -1) {
            jLabelTitle.setText("Modification d'un " + this.nomCategorie);
            jPanelModif.setName("Modifier");
            
            for (int i = 0; i < jTable.getColumnCount(); i++) {
                String columnName = jTable.getColumnName(i);
                Object columnValue = jTable.getValueAt(jTable.getSelectedRow(), i);
                
                for (Component unComponent : jPanelTextField.getComponents()) {
                    if (unComponent instanceof TextFieldPanel) {
                        if (unComponent.getName() == String.valueOf(columnName)) {
                            ((TextFieldPanel) unComponent).setTextField(String.valueOf(columnValue));
                        }
                    }
                }
            }
//            jTextFieldLibelle.setText((String) jTable.getValueAt(jTable.getSelectedRow(), jTable.getSelectedColumn()));
            jPanelModif.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez selectionner un " + this.nomCategorie);
        }
    }//GEN-LAST:event_jButtonModifierMouseClicked

    private void jButtonModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonModifierActionPerformed

    /**
     * Supprime un tuple de la table et la ligne correspondante dans le tableau si une ligne du tabelau est selectionnée
     * @param evt 
     */
    private void jButtonSupprimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSupprimerMouseClicked
        if (jTable.getSelectedRow() != -1) {
            try {
                ArrayList<String> lesValeurs = new ArrayList<String>();
                lesValeurs.add(String.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 0)));
                

                int reponse = DaoSIO.getInstance().requetePrepareAction(lesRequêtes.get(3), lesValeurs);
                
                if(reponse > 0){
                    DefaultTableModel leModel = (DefaultTableModel) jTable.getModel();
                    leModel.removeRow(jTable.getSelectedRow());
                    JOptionPane.showMessageDialog(this, "Suppression effectué");
                }else{
                    JOptionPane.showMessageDialog(this, "Un problème est survenue lors de la suppression");
                }

            } catch (Exception e) {
                Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez selectionner un profil");
        }
    }//GEN-LAST:event_jButtonSupprimerMouseClicked

    /**
     * Selon la valeur du name de jPanel Modif appel la fonction requetePrepareAction de la class DaoSIO pour effectuer un INSERT INTO ou un UPDATE
     * @param evt 
     */
    private void jButtonValiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonValiderMouseClicked
        switch (jPanelModif.getName()) {
            case "Creer":
                ArrayList<String> lesValeurs = new ArrayList<String>();
                for (Component unComponent : jPanelTextField.getComponents()) {
                    if(unComponent instanceof TextFieldPanel){
                        lesValeurs.add(String.valueOf(((TextFieldPanel) unComponent).getTextField()));
                    }
                }
                
                int reponse = DaoSIO.getInstance().requetePrepareAction(lesRequêtes.get(1), lesValeurs);
                if (reponse != 0) {
                    JOptionPane.showMessageDialog(this, "Insertion effectué");
                    break;
                } else {
                    JOptionPane.showMessageDialog(this, "Une erreur est survenu durant l'insertion");
                    break;
                }
            case "Modifier":
                
                ArrayList<String> lesValeursModif = new ArrayList<String>();
                for (Component unComponent : jPanelTextField.getComponents()) {
                    if(unComponent instanceof TextFieldPanel){
                        lesValeursModif.add(String.valueOf(((TextFieldPanel) unComponent).getTextField()));
                    }
                }
                lesValeursModif.add(String.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 0))); 
                
                int response = DaoSIO.getInstance().requetePrepareAction(lesRequêtes.get(2), lesValeursModif);
                if (response != 0) {
                    JOptionPane.showMessageDialog(this, "Insertion effectué");
                    break;
                } else {
                    JOptionPane.showMessageDialog(this, "Une erreur est survenu durant l'insertion");
                    break;
                }
            default:
                JOptionPane.showMessageDialog(this, "Une erreur est survenu");
                break;
        }
        jPanelModif.setVisible(false);
        clearTableProfil();
        try {
            InitTable();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonValiderMouseClicked

    private void jButtonAnnulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnnulerMouseClicked
        jPanelModif.setVisible(false);
    }//GEN-LAST:event_jButtonAnnulerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonCreer;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTitleTable;
    private javax.swing.JPanel jPanelModif;
    private javax.swing.JPanel jPanelTextField;
    private javax.swing.JScrollPane jScrollPaneTableProfil;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
