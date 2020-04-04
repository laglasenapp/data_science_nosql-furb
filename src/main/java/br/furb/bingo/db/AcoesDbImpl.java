package br.furb.bingo.db;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import br.furb.bingo.app.Configuration;
import redis.clients.jedis.Jedis;

public class AcoesDbImpl implements AcoesDB {

	private Jedis jedis;
	
	private Properties properties;
	
	private String memberIntervalKey;
	
	public AcoesDbImpl() {
		this.properties = Configuration.getInstance().getProperties();
		this.jedis = RedisConnection.getInstance().get();
		memberIntervalKey = properties.getProperty("memberKey");
		prepararDados();
	}
	
	private void prepararDados() {
		jedis.del(memberIntervalKey);
		int min = Integer.parseInt(properties.getProperty("sorteio.numeroMinimo"));
		System.out.println("Valor min�mo: " + min);
		int max = Integer.parseInt(properties.getProperty("sorteio.numeroMaximo"));
		System.out.println("Valor m�ximo: " + max);
		for(int i = min; i < max; i++) {
			jedis.sadd(memberIntervalKey, String.valueOf(i));
		}
		System.out.println("Lista foi criada para a chave: " + memberIntervalKey);
	}

	@Override
	public Set<Integer> obterValoresRandomicos() {
		int count = Integer.parseInt(properties.getProperty("cartela.quantidadeMaxPermitida"));
		System.out.println("Quantidade m�xima de n�meros permitidos em uma cartela: " + count);
		Set<String> resultadoStr = randomR(memberIntervalKey, count);
		Set<Integer> resultadoInt = resultadoStr.stream().map(Integer::parseInt).collect(Collectors.toSet()); 
		return resultadoInt;
	}
	
	private Set<String> randomR(String key, int count){
		List<String> resultado = jedis.srandmember(key, count);
		Set<String> resultadoSet = new HashSet<String>(resultado);
		if (resultadoSet.size() != count) {
			return randomR(key, count);
		}
		return resultadoSet;
	}

}