package br.furb.bingo.app;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public final class Configuration {

	private static final String NOME = "../../../../bingo.properties";

	private static final String JVM_ARG_PROPERTY_NAME = "propertiesPath";

	private static final Configuration instance = new Configuration();
	
	private Properties propriedades = new Properties();

	private Configuration() {
		try {
			carregar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Configuration getInstance() {
		return instance;
	}
	
	private void carregar() throws IOException {
		String caminhoArquivo = System.getProperty(JVM_ARG_PROPERTY_NAME);
		InputStream stream;
		if (StringUtils.isNotBlank(caminhoArquivo)) {
			File propertiesFile = new File(caminhoArquivo);
			if (!propertiesFile.exists()) {
				System.out.println("O arquivo n�o pode ser encontrado no diret�rio: " + caminhoArquivo);
			} else {
				System.out.println("Lendo as propriedades do arquivo: " + propertiesFile.toPath().toAbsolutePath());
				stream = new BufferedInputStream(new FileInputStream(propertiesFile));
				propriedades.load(stream);
			}
		} else {
				System.out.println("Lendo as propriedades do arquivo: " + getClass().getResource(NOME).getPath());
				stream = getClass().getResourceAsStream(NOME);
				propriedades.load(stream);
		}
	}

	public Properties getProperties() {
		return propriedades;
	}

}
