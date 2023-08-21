package club.j3studios.system.control;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import club.j3studios.system.b_windows.subwins.Facturar;
import club.j3studios.system.database.SQL;
import club.j3studios.system.model.Cajas;
import club.j3studios.system.utils.Tools;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

/**
 *
 * @author edison
 */
public class VentaPDF {

    private Facturar principal;
	
    private String nombreCliente;
    private String cedulaCliente;
    private String telefonoCliente;
    private String direccionCliente;

    private String cajero;
    private String fechaActual = "";
    private String horaActual = "";
    private String nombreArchivoPDFVenta = "";

    
    public VentaPDF(Facturar principal) {
		this.principal = principal;
	}

    public void DatosCliente(int idCliente) {
        Connection cn = SQL.conectar();
        String sql = "select * from tb_clientes where idCliente = '" + idCliente + "'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreCliente = rs.getString("nombre");
                cedulaCliente = rs.getString("cedula");
                telefonoCliente = rs.getString("telefono");
                direccionCliente = rs.getString("direccion");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del cliente: " + e);
        }
    }
    
    public void DatosCajero(Cajas caja) {
        new Tools().debug("Obteniendo datos del ID Cajero: " + caja.getIdCaja() + " - idUsuario: " + caja.getIdUsuario());
        
        Connection cn = SQL.conectar();
        String sql = "select * from tb_usuarios where idUsuario = '" + caja.getIdUsuario() + "'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cajero = rs.getString("nombre");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del cajero: " + e);
        }
    }
    
    //metodo para generar la factura de venta
    public void generarFacturaPDF(Cajas caja) {
        DatosCliente(principal.idCliente);
        DatosCajero(caja);
        
        try {
            Date date = new Date();
            fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(date);
            horaActual = new SimpleDateFormat("hh:mm:ss").format(date);
            
            String fechaNueva = "";
            for (int i = 0; i < fechaActual.length(); i++) {
                if (fechaActual.charAt(i) == '/') {
                    fechaNueva = fechaActual.replace("/", "_");
                }
            }
            String horaNueva = "";
            for (int i = 0; i < horaActual.length(); i++) {
                if (horaActual.charAt(i) == ':') {
                	horaNueva = horaActual.replace(":", "_");
                }
            }
            nombreArchivoPDFVenta = "Venta_" + fechaNueva + "_" + horaNueva + ".pdf";

            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);

            //Rectangle pageSize = new Rectangle(240f, 258f); //ancho y alto
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Font seniat = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
            Font title = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
            Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
            
            
            String ruc = "SENIAT\n";
            String nombre = "J3Studios C.A\n";
            String rif = "J09003966-0";
            String telefono = "(0212) 5528524";
            String direccion = "Urb. Llano Alto, Sector C, Manzana 5";
            String format = rif + "\nTeléfono: " + telefono + "\nDirección: " + direccion;
           
            Paragraph centroCabezado = new Paragraph();
            centroCabezado.add(new Phrase(ruc, seniat));
            centroCabezado.add(new Phrase(nombre, title));
            centroCabezado.add(new Phrase(format, negrita));
            centroCabezado.setAlignment(Element.ALIGN_CENTER);
            doc.add(centroCabezado);

            // SPACIO
            Paragraph ssssss = new Paragraph();
            ssssss.add(Chunk.NEWLINE);
            ssssss.add(Chunk.NEWLINE);
            ssssss.setAlignment(Element.ALIGN_CENTER);
            doc.add(ssssss);
            
            //
            PdfPTable tabla1 = new PdfPTable(3);
            tabla1.setWidthPercentage(100);
            tabla1.getDefaultCell().setBorder(0);
            float[] Columnatabla1 = new float[]{40f, 40f, 20f};
            tabla1.setWidths(Columnatabla1);
            tabla1.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph facturaNumber = new Paragraph();
            String invoiceNumber = "22312";
            if (invoiceNumber.length() < 7) {
            	invoiceNumber = String.format("%07d", Integer.parseInt(invoiceNumber));
            }
            facturaNumber.add(new Phrase(invoiceNumber, normal));
            facturaNumber.setAlignment(Element.ALIGN_RIGHT);
            
            Paragraph horaStr = new Paragraph();
            horaStr.add(new Phrase("HORA: "+horaActual, normal));
            horaStr.setAlignment(Element.ALIGN_RIGHT);
            
            Paragraph fechaStr = new Paragraph();
            fechaStr.add(new Phrase(fechaActual, normal));
            fechaStr.setAlignment(Element.ALIGN_RIGHT);
            
            tabla1.addCell(new Phrase("FACTURA: ", negrita));
            tabla1.addCell("");
            tabla1.addCell(facturaNumber);
            tabla1.addCell(new Phrase("FECHA: ", negrita));
            tabla1.addCell("");
            tabla1.addCell(fechaStr);
            tabla1.addCell(new Phrase("HORA: ", negrita));
            tabla1.addCell("");
            tabla1.addCell(horaStr);
            doc.add(tabla1);
                        
            // SPACIO
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("_____________________________________________________________________________");
            espacio.add(Chunk.NEWLINE);
            espacio.add(Chunk.NEWLINE);
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);
            
            //CUERPO
            PdfPTable tablaDatos = new PdfPTable(2);
            tablaDatos.setWidthPercentage(100);
            tablaDatos.getDefaultCell().setBorder(0);
            float[] ColumnaDatos = new float[]{35f, 35f};
            tablaDatos.setWidths(ColumnaDatos);
            tablaDatos.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cliente = new PdfPCell(new Phrase("Datos del cliente: ", negrita));
            PdfPCell vendedor = new PdfPCell(new Phrase("Vendedor: ", negrita));
            cliente.setBorder(0);
            vendedor.setBorder(0);
            tablaDatos.addCell(cliente);
            tablaDatos.addCell(vendedor);
            tablaDatos.addCell(new Phrase("V" + cedulaCliente + "\n" + nombreCliente + "\n" + telefonoCliente + "\n" + direccionCliente + "\n", normal));
            tablaDatos.addCell(new Phrase("" + cajero + "\n\n" + caja.getNombre().toUpperCase(), normal));
            doc.add(tablaDatos);
                                    
            //ESPACIO EN BLANCO
            Paragraph space0 = new Paragraph();
            space0.add(Chunk.NEWLINE);
            space0.add("_____________________________________________________________________________");
            space0.add(Chunk.NEWLINE);
            space0.add(Chunk.NEWLINE);
            space0.setAlignment(Element.ALIGN_CENTER);
            doc.add(space0);
            
            //AGREGAR LOS PRODUCTOS
            // CANTIDAD - DESCRIPCION - PRECIO UNIDAD - TOTAL
            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            //tama�o de celdas
             float[] ColumnaProducto = new float[]{15f, 50f, 30f, 20f};
             tablaProducto.setWidths(ColumnaProducto);
             tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
             PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: ", negrita));
             PdfPCell producto2 = new PdfPCell(new Phrase("Descripcion: ", negrita));
             PdfPCell producto3 = new PdfPCell(new Phrase("Precio: ", negrita));
             PdfPCell producto4 = new PdfPCell(new Phrase("Total: ", negrita));
             //quitar bordes
             producto1.setBorder(0);
             producto2.setBorder(0);
             producto3.setBorder(0);
             producto4.setBorder(0);
             //agregar color al encabezadi del producto
             producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
             producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);
             //agg celda a la tabla
             tablaProducto.addCell(producto1);
             tablaProducto.addCell(producto2);
             tablaProducto.addCell(producto3);
             tablaProducto.addCell(producto4);
            
            for(int i = 0; i < principal.table.getRowCount(); i++){
                String producto = principal.table.getValueAt(i, 2).toString();
                String cantidad = principal.table.getValueAt(i, 3).toString();
                String precio = principal.table.getValueAt(i, 4).toString();
                String iva = principal.table.getValueAt(i, 6).toString();
                String subtotal = principal.table.getValueAt(i, 5).toString();
                
                iva = iva.replace(".", "");
                iva = iva.replace(",", ".");
                
                tablaProducto.addCell(new Phrase(cantidad, normal));
                tablaProducto.addCell(new Phrase(producto + (Double.valueOf(iva.substring(3, iva.length())) == 0.0 ? " (E)" : ""), normal));
                tablaProducto.addCell(new Phrase(precio, normal));
                tablaProducto.addCell(new Phrase(subtotal, normal));
            }
            doc.add(tablaProducto);
            
            //ESPACIO EN BLANCO
            Paragraph space1 = new Paragraph();
            space1.add(Chunk.NEWLINE);
            space1.add("_____________________________________________________________________________");
            space1.add(Chunk.NEWLINE);
            space1.add(Chunk.NEWLINE);
            space1.setAlignment(Element.ALIGN_CENTER);
            doc.add(space1);
            
            //Total pagar
            PdfPTable tablaFinal = new PdfPTable(5);
            tablaFinal.setWidthPercentage(100);
            tablaFinal.getDefaultCell().setBorder(0);
            float[] ColumnaFinal = new float[]{60f, 65f, 65f, 50f, 50f};
            tablaFinal.setWidths(ColumnaFinal);
            tablaFinal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaFinal.addCell(" ");
            tablaFinal.addCell(" ");
            tablaFinal.addCell(" ");
            tablaFinal.addCell(new Phrase("Subtotal\n\nIVA (16%)\n\nTotal", negrita));
            tablaFinal.addCell(new Phrase(""+principal.labelSubTotal.getText() + "\n\n" + principal.label_Iva.getText() + "\n\n" + principal.labelTotal.getText(), normal));
            doc.add(tablaFinal);
                                   
            //Mensaje
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add(Chunk.NEWLINE);
            mensaje.add(new Phrase("¡Gracias por su compra!\n", negrita));
            mensaje.add(new Phrase("WWW.J3STUDIOS.STORE", title));
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
           
           //cerrar el documento y el archivo
            doc.close();
            archivo.close();
           
           //abrir el documento al ser generado automaticamente
            Desktop.getDesktop().open(file);
            
            
        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
    }

}
