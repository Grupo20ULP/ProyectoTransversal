/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vistas;

import Modelo.Materia;
import Persistencia.MateriaData;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Carreño Lucas
 */
public class VistaMaterias extends javax.swing.JInternalFrame {

    private MateriaData materiaData;
    private DefaultTableModel modeloTabla;
    private int idMateriaSeleccionada = -1;

    /**
     * Creates new form VistaMaterias
     */
    public VistaMaterias () {
        initComponents();
        materiaData = new MateriaData();
        configurarTabla();
        materiasEnTabla();
        configurarTablaDeSeleccion();
        agregarListenerTabla();
    }

    private void configurarTabla () {
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Estado");
        tablaMaterias.setModel(modeloTabla);
        tablaMaterias.setSelectionMode(
            javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablaMaterias.getColumnModel().
            getColumnCount() > 0) {
            tablaMaterias.getColumnModel().
                getColumn(0).
                setMaxWidth(0);
            tablaMaterias.getColumnModel().
                getColumn(0).
                setMinWidth(0);
            tablaMaterias.getColumnModel().
                getColumn(0).
                setPreferredWidth(0);
            tablaMaterias.getColumnModel().
                getColumn(0).
                setResizable(false);
        }
    }
    private DefaultTableModel modeloTablaSeleccion;

    private void configurarTablaDeSeleccion () {
        modeloTablaSeleccion = new DefaultTableModel() {
            @Override
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        modeloTablaSeleccion.addColumn("Nombre");
        modeloTablaSeleccion.addColumn("Año");
        modeloTablaSeleccion.addColumn("Estado");
        tablaSeleccion.setModel(modeloTablaSeleccion);
    }

    private void llenarTablaSeleccion (int filaSeleccionada) {
        modeloTablaSeleccion.setRowCount(0);
        String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        Object anio = modeloTabla.getValueAt(filaSeleccionada, 2);
        String estado = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
        modeloTablaSeleccion.addRow(new Object[]{
            nombre,
            anio,
            estado
        });
        tablaSeleccion.setModel(modeloTablaSeleccion);
    }

    private void materiasEnTabla () {
        modeloTabla.setRowCount(0);
        List<Materia> listaMaterias = materiaData.actualizarmateria();
        for (Materia m : listaMaterias) {
            modeloTabla.addRow(new Object[]{
                m.getIdMateria(),
                m.getNombre(),
                m.getAnio(),
                m.isEstado()
                ? "Activo"
                : "Inactivo"
            });
        }
    }

    private void limpiarCampos () {
        txtNombre.setText("");
        txtAnio.setText("");
        cboxEstado.setSelectedIndex(0);
        idMateriaSeleccionada = -1;
        tablaMaterias.clearSelection();
    }

    private void agregarListenerTabla () {
        tablaMaterias.getSelectionModel().
            addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged (ListSelectionEvent e) {
                    if ( ! e.getValueIsAdjusting() && tablaMaterias.
                        getSelectedRow() != -1) {
                        int fila = tablaMaterias.getSelectedRow();
                        idMateriaSeleccionada = (int) modeloTabla.getValueAt(
                            fila, 0);
                        llenarTablaSeleccion(fila);
                    }
                }
            });
    }

    private void cargarMateriasInactivas () {
        modeloTabla.setRowCount(0);
        List<Materia> lista = materiaData.materiasInactivas();
        for (Materia m : lista) {
            modeloTabla.addRow(new Object[]{
                m.getIdMateria(),
                m.getNombre(),
                m.getAnio(),
                m.isEstado()
                ? "Activo"
                : "Inactivo"
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jProgressBar1 = new javax.swing.JProgressBar();
        lblMaterias = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        lblAnio = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMaterias = new javax.swing.JTable();
        btnInsertar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        cboxEstado = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaSeleccion = new javax.swing.JTable();

        jScrollPane2.setViewportView(jTree1);

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(408, 480));

        lblMaterias.setFont(new java.awt.Font("Noto Sans Hebrew", 1, 24)); // NOI18N
        lblMaterias.setText("Materias");

        txtNombre.setFont(new java.awt.Font("Noto Sans Hebrew", 1, 12)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtAnio.setFont(new java.awt.Font("Noto Sans Hebrew", 1, 12)); // NOI18N
        txtAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioActionPerformed(evt);
            }
        });

        lblAnio.setFont(new java.awt.Font("Noto Sans Hebrew", 1, 12)); // NOI18N
        lblAnio.setText("Año:");

        lblNombre.setFont(new java.awt.Font("Noto Sans Hebrew", 1, 12)); // NOI18N
        lblNombre.setText("Nombre:");

        tablaMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Materias", "Año", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaMaterias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaMaterias.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tablaMaterias);
        if (tablaMaterias.getColumnModel().getColumnCount() > 0) {
            tablaMaterias.getColumnModel().getColumn(0).setResizable(false);
            tablaMaterias.getColumnModel().getColumn(1).setResizable(false);
            tablaMaterias.getColumnModel().getColumn(2).setResizable(false);
        }

        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnAlta.setText("Alta Logica");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        btnBaja.setText("Baja Logica");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        lblEstado.setFont(new java.awt.Font("Noto Sans Hebrew", 1, 12)); // NOI18N
        lblEstado.setText("Estado:");

        cboxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Opcion", "Activo", "Inactivo" }));

        tablaSeleccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaSeleccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Materias", "Año", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaSeleccion.setColumnSelectionAllowed(true);
        tablaSeleccion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaSeleccion.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tablaSeleccion);
        tablaSeleccion.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablaSeleccion.getColumnModel().getColumnCount() > 0) {
            tablaSeleccion.getColumnModel().getColumn(0).setResizable(false);
            tablaSeleccion.getColumnModel().getColumn(1).setResizable(false);
            tablaSeleccion.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(lblMaterias))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblAnio)
                            .addComponent(lblEstado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAnio)
                            .addComponent(txtNombre))
                        .addGap(43, 43, 43)
                        .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btnAlta)
                        .addGap(18, 18, 18)
                        .addComponent(btnBaja))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBorrar)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMaterias)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAnio)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstado)
                            .addComponent(cboxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrar)
                    .addComponent(btnActualizar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta)
                    .addComponent(btnBaja))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        cargarMateriasInactivas();
        JOptionPane.showMessageDialog(this,
            "Lista actualizada con todas las materias y ordenada alfabeticamente.",
            "", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        if (idMateriaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar una materia de la tabla para eliminarla.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Esta seguro de eliminar la materia ID: " + idMateriaSeleccionada
            + "?", "Confiramacion", JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                materiaData.borrarMateria(idMateriaSeleccionada);
                limpiarCampos();
                materiasEnTabla();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al borrar la materia: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombre.getText().
            trim();
        String anioo = txtAnio.getText().
            trim();
        String estadoo = cboxEstado.getSelectedItem().
            toString();
        if (nombre.isEmpty() || anioo.isEmpty() || estadoo.equalsIgnoreCase(
            "Seleccione opcion")) {
            JOptionPane.showMessageDialog(this,
                "Debe completar nombre, año y estado.");
            return;
        }
        try {
            int anio = Integer.parseInt(anioo);
            if (materiaData.materiaDuplicada(nombre, anio)) {
                JOptionPane.showMessageDialog(this,
                    "Error ya existe la materia '' " + nombre
                    + " '' para el año " + anio + " y no se puede insertar.",
                    "Materia duplicada", JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean estado = estadoo.equalsIgnoreCase("Activo");
            Materia nuevaMateria = new Materia(nombre, anio, estado);
            materiaData.insertarMateria(nuevaMateria);
            limpiarCampos();
            materiasEnTabla();
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error ingrese un año valido.");
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al insertar materia, ya existe.");
        }
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // TODO add your handling code here:
        if (idMateriaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar una materia de la tabla para dar de baja.");
            return;
        }
        try {
            materiaData.bajaLogica(idMateriaSeleccionada);
            limpiarCampos();
            materiasEnTabla();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al dar la baja logica: "
                + ex.getMessage());
        }
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        // TODO add your handling code here:
        if (idMateriaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                "Error debe seleccionar una materia de la tabla.",
                "materia no esta seleccionada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Materia materiaParaAlta = new Materia();
            materiaParaAlta.setIdMateria(idMateriaSeleccionada);
            materiaData.altaLogica(materiaParaAlta);
            limpiarCampos();
            materiasEnTabla();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al dar de alta la materia: " + ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAltaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JComboBox<String> cboxEstado;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblMaterias;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTable tablaMaterias;
    private javax.swing.JTable tablaSeleccion;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
