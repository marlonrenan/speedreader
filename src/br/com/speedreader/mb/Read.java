package br.com.speedreader.mb;


import java.io.File;
import java.io.FileOutputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class Read {
	
	
	private static final String PATH = "C:\\pdf\\";

	private Integer startPage;
	
	private Integer endPage;
	
	private String texto;

	private String filename;


	

	@PostConstruct
	public void init(){
		
		
		
	}
	
	public void doUpload(FileUploadEvent event) {
		
		/**
		 *  
		UploadedFile uploadedFile = fileUploadEvent.getFile(); 
		String fileNameUploaded = uploadedFile.getFileName(); 
		long fileSizeUploaded = uploadedFile.getSize(); 
		String infoAboutFile = "<br/> Arquivo recebido: <b>" +fileNameUploaded +"</b><br/>"+ "Tamanho do Arquivo: <b>"+fileSizeUploaded+"</b>"; 
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		facesContext.addMessage(null, new FacesMessage("Sucesso", infoAboutFile));
		
		*/ 
		
		
		
		try { 
			/*ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse(); 

			FacesContext aFacesContext = FacesContext.getCurrentInstance(); 
			ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext(); 

			String realPath = context.getRealPath("/");*/
			
			String path = "";
			filename = event.getFile().getFileName();

			// Aqui cria o diretorio caso não exista 
			File file = new File(PATH); 
			file.mkdirs();

			byte[] arquivo = event.getFile().getContents(); 
			String caminho = PATH + filename;

			// esse trecho grava o arquivo no diretório 
			FileOutputStream fos = new FileOutputStream(caminho); 
			fos.write(arquivo); 
			fos.close(); 

			
			FacesContext facesContext = FacesContext.getCurrentInstance(); 
			facesContext.addMessage(null, new FacesMessage("Sucesso", "Arquivo enviado com sucesso.."));

		} catch (Exception ex) { 
				System.out.println("Erro no upload de imagem" + ex); 
		} 

			
		
	}

	
	
	public void carregar(ActionEvent event) {
		
		
		PDDocument pdfDocument = null;
		
		try {
			
			pdfDocument = PDDocument.load(PATH+filename);
			PDFTextStripper stripper = new PDFTextStripper();
			
			stripper.setStartPage(startPage);
			stripper.setEndPage(endPage);
			
			texto = stripper.getText(pdfDocument);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("não foi possivel ler o documento");
		}
		
		
	}
	
	
	public Integer getStartPage() {
		return startPage;
	}


	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}


	public Integer getEndPage() {
		return endPage;
	}


	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	

	
}
