/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.InfoPie.screens;

import java.sql.*;
import br.com.InfoPie.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author debs
 */
public class TelaClientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaClientes
     */
    public TelaClientes() {
        initComponents();
        conexao = ModuloConexao.conector();

    }

    private void adicionar() {
        String sql = ("insert into tb_clientes(nome_cliente,end_cliente,fone_cliente,email_cliente) values(?,?,?,?)");
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClienteNome.getText());
            pst.setString(2, txtClienteEnd.getText());
            pst.setString(3, txtClienteFone.getText());
            pst.setString(4, txtClienteMail.getText());

            if ((txtClienteNome.getText().isEmpty()) || (txtClienteFone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
            } else {
                int add = pst.executeUpdate();
                if (add > 0) { //0 falso e 1 Ok 
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso! ");
                    txtClienteNome.setText(null);
                    txtClienteEnd.setText(null);
                    txtClienteFone.setText(null);
                    txtClienteMail.setText(null);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void Edita_Cliente() {
        String sql = "update tb_clientes set nome_cliente = ?,end_cliente=?,fone_cliente=?,email_cliente=? where id_cliente = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClienteNome.getText());
            pst.setString(2, txtClienteFone.getText());
            pst.setString(3, txtClienteEnd.getText());
            pst.setString(4, txtClienteMail.getText());
            pst.setString(5, txtClienteId.getText());
            if ((txtClienteNome.getText().isEmpty()) || (txtClienteFone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
            } else {
                //Add CLIENTES
                int add = pst.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso! ");
                    
                    txtClienteNome.setText(null);
                    txtClienteFone.setText(null);
                    txtClienteEnd.setText(null);
                    txtClienteMail.setText(null);
                    btnClienteAdd.setEnabled(true);
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    private void remove_cliente() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o Cliente ? ", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tb_clientes where id_cliente=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtClienteId.getText());
                int deletado = pst.executeUpdate();
                if (deletado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    txtClienteId.setText(null);
                    txtClienteNome.setText(null);
                    txtClienteFone.setText(null);
                    txtClienteEnd.setText(null);
                    txtClienteMail.setText(null);
                    btnClienteAdd.setEnabled(true);
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //metodo para pesquisa de clientes com filtro
    private void pesquisa_cliente() {
        String sql = "select * from tb_clientes where nome_cliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passa o conteudo da caixa de pesquisa txtClientePequisar para a interrogação
            //"%" é a continuação da String SQL
            pst.setString(1, txtClienteFinder.getText() + "%");
            rs = pst.executeQuery();
            //Usando a biblioteca rs2xml.jar
            //Preenche a tabela tblClientes do FORM
            //Pesquisa em tempo real
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo SETA campos da tabela do banco de dados para o form
    public void setar() {
        int setar = tblClientes.getSelectedRow();
        txtClienteId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtClienteNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtClienteFone.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtClienteEnd.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtClienteMail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        
        btnClienteAdd.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtClienteNome = new javax.swing.JTextField();
        txtClienteMail = new javax.swing.JTextField();
        txtClienteEnd = new javax.swing.JTextField();
        txtClienteFone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtClienteFinder = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnClienteAdd = new javax.swing.JButton();
        btnClienteEditar = new javax.swing.JButton();
        btnClienteRemover = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtClienteId = new javax.swing.JTextField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tela de clientes");
        setPreferredSize(new java.awt.Dimension(929, 562));

        jLabel1.setText("* Nome:");

        jLabel2.setText("* Telefone:");

        jLabel3.setText("E-mail:");

        jLabel4.setText("Endereço:");

        txtClienteNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteNomeActionPerformed(evt);
            }
        });

        txtClienteEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteEndActionPerformed(evt);
            }
        });

        jLabel8.setText("* Campos obrigatorios");

        txtClienteFinder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteFinderActionPerformed(evt);
            }
        });
        txtClienteFinder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteFinderKeyReleased(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/finderpq.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        tblClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        btnClienteAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/add.png"))); // NOI18N
        btnClienteAdd.setPreferredSize(new java.awt.Dimension(70, 70));
        btnClienteAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteAddActionPerformed(evt);
            }
        });

        btnClienteEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/edit.png"))); // NOI18N
        btnClienteEditar.setPreferredSize(new java.awt.Dimension(70, 70));
        btnClienteEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteEditarActionPerformed(evt);
            }
        });

        btnClienteRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/InfoPie/icons/delete.png"))); // NOI18N
        btnClienteRemover.setPreferredSize(new java.awt.Dimension(70, 70));
        btnClienteRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteRemoverActionPerformed(evt);
            }
        });

        jLabel5.setText("ID");

        txtClienteId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(26, 26, 26)
                                .addComponent(txtClienteFinder, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtClienteMail, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtClienteFone, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(270, 270, 270))
                                                    .addComponent(txtClienteNome)
                                                    .addComponent(txtClienteEnd)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClienteAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClienteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClienteRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtClienteFinder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtClienteFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtClienteEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtClienteMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClienteAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClienteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClienteRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        setBounds(0, 0, 929, 562);
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteEndActionPerformed

    private void txtClienteFinderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteFinderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteFinderActionPerformed

    private void txtClienteNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteNomeActionPerformed

    private void btnClienteRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteRemoverActionPerformed
        // TODO add your handling code here:
        remove_cliente();
    }//GEN-LAST:event_btnClienteRemoverActionPerformed

    private void btnClienteEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteEditarActionPerformed
        // Metodo Editar Cliente
        Edita_Cliente();
    }//GEN-LAST:event_btnClienteEditarActionPerformed

    private void btnClienteAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteAddActionPerformed
        //Metodo adicionar cliente
        adicionar();
    }//GEN-LAST:event_btnClienteAddActionPerformed

    private void tblClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClientesKeyReleased

    }//GEN-LAST:event_tblClientesKeyReleased

    private void txtClienteFinderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteFinderKeyReleased

        //Enquando digita aparece os usuarios
        pesquisa_cliente();
    }//GEN-LAST:event_txtClienteFinderKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
        //clicando com o mouse
        //seta campos
        setar();
    }//GEN-LAST:event_tblClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClienteAdd;
    private javax.swing.JButton btnClienteEditar;
    private javax.swing.JButton btnClienteRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtClienteEnd;
    private javax.swing.JTextField txtClienteFinder;
    private javax.swing.JTextField txtClienteFone;
    private javax.swing.JTextField txtClienteId;
    private javax.swing.JTextField txtClienteMail;
    private javax.swing.JTextField txtClienteNome;
    // End of variables declaration//GEN-END:variables
}
