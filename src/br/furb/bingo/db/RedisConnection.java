package br.furb.bingo.db;

import java.util.Properties;

import br.furb.bingo.app.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Classe singleton para conexão com o banco
 * 
 * @author Luiz
 *
 */
public final class RedisConnection {
	
	/** Nome da propriedade referente ao hostname do banco */
	private static final String DB_HOSTNAME_KEY = "db.hostname";
	
	/** Nome da propriedade referente a porta do banco */
	private static final String DB_PORT_KEY = "db.port";
	
	/** Hostname padrão */
	private static final String DB_HOSTNAME_DEFAULT = "localhost";
	
	/** Porta padrão */
	private static final int DB_PORT_DEFAULT = 6379;

	/** Conexão */
	private static RedisConnection connection;
	
	/** Conexão ao Jedis */
	private Jedis jedis;
	
	/** Método construtor */
	private RedisConnection() {
		Properties properties = Configuration.getInstance().getProperties();
		String host = (String) properties.getOrDefault(DB_HOSTNAME_KEY, DB_HOSTNAME_DEFAULT);
		System.out.println("Hostname do banco de dados: " + host);
		int porta = Integer.parseInt((String) properties.getOrDefault(DB_PORT_KEY, DB_PORT_DEFAULT));
		System.out.println("Porta do banco: " + porta);
		this.jedis = new Jedis(host, porta);
	}
	
	/** Método para obter uma instância da classe */
	public static RedisConnection getInstance() {
		if (connection == null) {
			connection = new RedisConnection();
		}
		return connection;
	}
	
	/** Obter a instância do Jedis */
	public Jedis get() {
		return jedis;
	}

}
