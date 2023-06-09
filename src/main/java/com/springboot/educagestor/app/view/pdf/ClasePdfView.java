package com.springboot.educagestor.app.view.pdf;


import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("horario")
public class ClasePdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String[][] matrizHorario=(String[][]) model.get("matrizHorario");
		String semestre=(String) model.get("semestre");
		String nombreAlumno =(String) model.get("nombreAlumno");
		
		
		PdfPTable tablaEncabezado = new PdfPTable(1);
		PdfPCell cellHead = new PdfPCell(new Phrase(semestre,new Font(Font.HELVETICA, 12)));
		cellHead.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		tablaEncabezado.addCell(cellHead);
		
		cellHead = new PdfPCell(new Phrase("Alumno: ".concat(nombreAlumno),new Font(Font.HELVETICA, 12)));
		cellHead.setHorizontalAlignment(Element.ALIGN_CENTER);
		tablaEncabezado.addCell(cellHead);
		
		PdfPTable tabla = new PdfPTable(6);
		
		tabla.addCell("");
		tabla.addCell("Lunes");
		tabla.addCell("Martes");
		tabla.addCell("Miercoles");
		tabla.addCell("Jueves");
		tabla.addCell("Viernes");
		
		Font font = new Font(Font.HELVETICA, 10);
		Phrase phrase = new Phrase();
		PdfPCell cell = new PdfPCell();

		for (int i = 1; i < matrizHorario.length; i++) {
			for (int j = 0; j < matrizHorario[i].length; j++) {
				
				
				 phrase = new Phrase(matrizHorario[i][j], font);
				 cell = new PdfPCell(phrase);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);

				
				
				
				tabla.addCell(cell);
				
			}
		}
		
		
		document.add(tablaEncabezado);
		document.add(tabla);
		
	}

}
