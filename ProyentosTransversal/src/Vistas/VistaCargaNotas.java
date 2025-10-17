/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vistas;

import Modelo.Alumno;
import Modelo.Cursada;
import Modelo.Materia;
import Persistencia.AlumnoData;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import Persistencia.CursadaData;
import javax.swing.table.DefaultTableModel;
import Persistencia.MateriaData;

public class VistaCargaNotas extends javax.swing.JInternalFrame {

    private MateriaData materiaData;
    private CursadaData cursadaData;
    private DefaultTableModel modeloTabla;
    private AlumnoData alumnoData;
    private DefaultComboBoxModel<String> comboModel;

    /**
     * Creates Gomez Heber,Carreras Juan, Zerdá Nehuen , Galan Federico, Carreño
     * Lucas
     */
    public VistaCargaNotas () {
        initComponents();
        inicializarComponentes();
    }

    private void inicializarComponentes () {
        try {
            // Inicializar AlumnoData
            alumnoData = new AlumnoData();
            cursadaData = new CursadaData();
            modeloTabla = (DefaultTableModel) jTableAlumnos.getModel();
            // Inicializar el modelo del combo
            comboModel = new DefaultComboBoxModel<>();
            jComboBoxAlumnos.setModel(comboModel);
            // Cargar alumnos
            cargarAlumnosEnComboBox();
            // Configurar botones
            jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                }
            });
            jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed (java.awt.event.ActionEvent evt) {
                    cancelar();
                }
            });
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al inicializar: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarAlumnosEnComboBox () {
        try {
            comboModel.removeAllElements();
            comboModel.addElement("Seleccione un alumno");
            List<Alumno> alumnos = alumnoData.actualizarAlumno();
            if (alumnos != null &&  ! alumnos.isEmpty()) {
                for (Alumno alumno : alumnos) {
                    String item = alumno.getIdAlumno() + " - "
                        + alumno.getApellido() + ", "
                        + alumno.getNombre()
                        + " (DNI: " + alumno.getDni() + ")";
                    comboModel.addElement(item);
                }
            }
            else {
                comboModel.addElement("No hay alumnos registrados");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar alumnos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            comboModel.addElement("Error al cargar datos");
        }
    }

    private void cancelar () {
        this.dispose();
    }

    private void agregarEventosTabla () {
        // Listener para doble click en la columna de nota
        jTableAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked (java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = jTableAlumnos.getSelectedRow();
                    int col = jTableAlumnos.getSelectedColumn();
                    if (col == 2) { // Solo columna Nota
                        Object value = jTableAlumnos.getValueAt(row, col);
                        System.out.println("Doble click en nota: " + value);
                        // Aquí podés abrir un JOptionPane para editar la nota manualmente si querés
                        String input = JOptionPane.showInputDialog(
                            null,
                            "Modificar nota:",
                            value
                        );
                        if (input != null) {
                            try {
                                double nuevaNota = Double.parseDouble(input);
                                jTableAlumnos.setValueAt(nuevaNota, row, col);
                            }
                            catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null,
                                    "Valor inválido, debe ser un número.");
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxAlumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlumnos = new javax.swing.JTable();
        jBtnGuardar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setTitle("Edicion de notas");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Unispace", 2, 36)); // NOI18N
        jLabel1.setText("Cargar Notas");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Seleccione Un Alumno");

        jComboBoxAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAlumnosActionPerformed(evt);
            }
        });

        jTableAlumnos.setBackground(new java.awt.Color(204, 204, 204));
        jTableAlumnos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTableAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Alumno", "Materia", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableAlumnos);

        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnCancelar)
                .addGap(18, 18, 18)
                .addComponent(jBtnGuardar)
                .addGap(38, 38, 38))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jLabel1)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnGuardar)
                    .addComponent(jBtnCancelar))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAlumnosActionPerformed
        int index = jComboBoxAlumnos.getSelectedIndex();
        if (index <= 0) {
            // Limpia la tabla si no hay selección válida
            ((javax.swing.table.DefaultTableModel) jTableAlumnos.getModel()).
                setRowCount(0);
            return;
        }
        try {
            // Extraer ID del alumno del texto del combo
            String item = (String) jComboBoxAlumnos.getSelectedItem();
            int idAlumno = Integer.parseInt(item.split(" - ")[0].trim());
            // Obtener las cursadas del alumno
            Persistencia.CursadaData cursadaData
                = new Persistencia.CursadaData();
            java.util.List<Modelo.Cursada> cursadas = cursadaData.
                obtenerCursadasPorAlumno(idAlumno);
            // Cargar datos en la tabla
            javax.swing.table.DefaultTableModel modeloTabla
                = (javax.swing.table.DefaultTableModel) jTableAlumnos.getModel();
            modeloTabla.setRowCount(0); // Limpia tabla
            for (Modelo.Cursada cursada : cursadas) {
                modeloTabla.addRow(new Object[]{
                    cursada.getAlumno().
                    getApellido() + ", " + cursada.getAlumno().
                    getNombre(),
                    cursada.getMateria().
                    getNombre(),
                    cursada.getNota()
                });
            }
        }
        catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Error al cargar las notas: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBoxAlumnosActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        int indexCombo = jComboBoxAlumnos.getSelectedIndex();
        if (indexCombo <= 0) {
            JOptionPane.showMessageDialog(this,
                "Seleccione un alumno para guardar las notas.");
            return;
        }
        try {
            String item = (String) jComboBoxAlumnos.getSelectedItem();
            int idAlumno = Integer.parseInt(item.split(" - ")[0].trim());
            AlumnoData ad = new AlumnoData();
            Alumno alumno = ad.buscarAlumnoPorId(idAlumno);
            CursadaData cd = new CursadaData();
            MateriaData md = new MateriaData();
            List<Materia> todasMaterias = md.obtenerMaterias();
            DefaultTableModel modelo = (DefaultTableModel) jTableAlumnos.
                getModel();
            for (int i = 0; i < modelo.getRowCount(); i ++) {
                String nombreMateria = ((String) modelo.getValueAt(i, 1)).trim();
                double nota = 0;
                Object valor = modelo.getValueAt(i, 2);
                if (valor != null) {
                    nota = Double.parseDouble(valor.toString());
                }
                // Encontrar la materia correcta
                Materia materia = null;
                for (Materia m : todasMaterias) {
                    if (m.getNombre().
                        trim().
                        equalsIgnoreCase(nombreMateria)) {
                        materia = m;
                        break;
                    }
                }
                if (materia == null) {
                    continue;
                }
                // Verificar si ya existe la cursada
                Cursada cursadaExistente = null;
                List<Cursada> cursadasAlumno = cd.obtenerCursadasPorAlumno(
                    idAlumno);
                for (Cursada c : cursadasAlumno) {
                    if (c.getMateria().
                        getIdMateria() == materia.getIdMateria()) {
                        cursadaExistente = c;
                        break;
                    }
                }
                if (cursadaExistente != null) {
                    // Actualiza nota
                    cd.actualizarNota(idAlumno, materia.getIdMateria(), nota);
                }
                else {
                    // Inserta nueva cursada
                    Cursada nueva = new Cursada();
                    nueva.setAlumno(alumno);
                    nueva.setMateria(materia);
                    nueva.setNota(nota);
                    cd.guardarCursada(nueva);
                }
            }
            // Recargar tabla desde BD para asegurar que está sincronizada
            cargarCursadasTabla(idAlumno);
            JOptionPane.
                showMessageDialog(this, "Notas guardadas correctamente.");
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,
                "Formato de nota inválido, debe ser un número.");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar notas: " + e.
                getMessage());
        }
    }

    private void cargarCursadasTabla (int idAlumno) {
        DefaultTableModel modelo = (DefaultTableModel) jTableAlumnos.getModel();
        modelo.setRowCount(0);
        CursadaData cd = new CursadaData();
        List<Cursada> cursadas = cd.obtenerCursadasPorAlumno(idAlumno);
        for (Cursada c : cursadas) {
            modelo.addRow(new Object[]{
                c.getAlumno().
                getApellido() + ", " + c.getAlumno().
                getNombre(),
                c.getMateria().
                getNombre(),
                c.getNota()
            });
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JComboBox<String> jComboBoxAlumnos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAlumnos;
    // End of variables declaration//GEN-END:variables
}
