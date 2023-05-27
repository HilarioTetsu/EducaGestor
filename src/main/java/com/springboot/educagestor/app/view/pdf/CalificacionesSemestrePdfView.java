package com.springboot.educagestor.app.view.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.springboot.educagestor.app.models.dto.CalificacionDTO;
import com.springboot.educagestor.app.models.entity.Materia;
import com.springboot.educagestor.app.models.services.IMateriaService;

@Component("calificacionesSemestrePDF")
public class CalificacionesSemestrePdfView extends AbstractPdfView{

	@Autowired
	private IMateriaService materiaService;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		Map<String, List<CalificacionDTO>> mapCalif = (Map<String, List<CalificacionDTO>>) model.get("mapCalificaciones");
		String alumnoNombre=(String) model.get("alumnoNombre");
		
		String semestreNombre = (String) model.get("semestreNombreCompleto");
		
		String carreraAlumno = (String) model.get("carreraAlumno");
		
		int maxUnidades=(int) model.get("maxUnidades");
		
		PdfPTable tablaEncabezado = new PdfPTable(1);
		PdfPCell cellHead = new PdfPCell(new Phrase(semestreNombre,new Font(Font.HELVETICA, 12)));
		cellHead.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		tablaEncabezado.addCell(cellHead);
		
		cellHead = new PdfPCell(new Phrase("Alumno: ".concat(alumnoNombre),new Font(Font.HELVETICA, 12)));
		cellHead.setHorizontalAlignment(Element.ALIGN_CENTER);
		tablaEncabezado.addCell(cellHead);
		
		cellHead = new PdfPCell(new Phrase("Carrera: ".concat(carreraAlumno),new Font(Font.HELVETICA, 12)));
		cellHead.setHorizontalAlignment(Element.ALIGN_CENTER);
		tablaEncabezado.addCell(cellHead);
		
		PdfPTable tabla = new PdfPTable(maxUnidades+1);
		tabla.addCell("Materias");
		for (int i = 1; i <= (maxUnidades); i++) {
			tabla.addCell("Unidad "+i);
		}
		
		Materia materia=new Materia();
		for (Map.Entry<String, List<CalificacionDTO>> entry : mapCalif.entrySet()) {
			
			materia=materiaService.findByMateriaId(entry.getKey());
			
			tabla.addCell(materia.getNombre()+"\n"+materia.getMateriaId()+"\n");
			
			for (CalificacionDTO item : entry.getValue()) {
				tabla.addCell(item.getCalificacion().toString());
			}
			tabla.completeRow();
		}
		
		
		float firstColumnWidth = 220;
        float[] columnWidths = new float[maxUnidades+1];
        columnWidths[0] = firstColumnWidth;
        
        // Calcular el ancho relativo para las demás columnas
        float remainingWidth = (PageSize.A4.getWidth() - firstColumnWidth) / (maxUnidades+1 - 1);
        for (int i = 1; i < maxUnidades+1; i++) {
            columnWidths[i] = remainingWidth;
        }
        
        tabla.setWidths(columnWidths);
		
		
		
		document.add(tablaEncabezado);
		document.add(tabla);
		
	}

}
