package br.furb.bingo.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.furb.bingo.app.Configuration;
import br.furb.bingo.db.AcoesDB;
import br.furb.bingo.db.AcoesDbImpl;
import br.furb.bingo.model.Cartela;
import br.furb.bingo.model.Usuario;

/**
 * Classe que implementa a interface {@link Operacoes}  
 * 
 * @author Luiz
 *
 */
public class OperacoesImpl implements Operacoes {

	/** Atributo {@link AcoesDB} para as ações do banco */
	private AcoesDB acoes;
	
	/** Atributo {@link Properties} contendo as propriedades do sistema */
	private Properties properties;
	
	/** Atributo {@link SorteioNumero} para realização dos sorteios */
	private SorteioNumero sorteioNumero;
	
	/** Lista de usuários */
	private List<Usuario> usuarios;
	
	/** Método construtor */
	public OperacoesImpl() {
		properties = Configuration.getInstance().getProperties();
		acoes = new AcoesDbImpl();
		Integer max = Integer.parseInt(properties.getProperty("sorteio.numeroMaximo"));
		Integer min = Integer.parseInt(properties.getProperty("sorteio.numeroMinimo"));
		sorteioNumero = new SorteioNumero(max, min);
	}
	
	@Override
	public void gerarESalvarCartelas() {
		int quantidadeCartelas = Integer.parseInt(properties.getProperty("participantes"));
		usuarios = new ArrayList<Usuario>(quantidadeCartelas);
		for (int i = 0; i < quantidadeCartelas; i++) {
			Set<Integer> numerosAleatorios = acoes.obterValoresRandomicos();
			
			Cartela cartela = new Cartela(i);
			cartela.adicionarNumeros(numerosAleatorios);
			
			Usuario usuario = new Usuario(i, cartela);
			
			usuarios.add(usuario);
		}
		acoes.salvarUsuarios(usuarios);
	}

	@Override
	public void sortearPedraEIncrementarScore() throws PossibilidadeSorteioEsgotadasException {
		Integer numeroSorteado = sorteioNumero.sortear();
		System.out.println("Número sorteado: " + numeroSorteado);
		List<Usuario> listaPessoaComNumeroSorteadoNaCartela = listaPessoaComNumeroNaCartela(numeroSorteado);
		acoes.incrementarScore(listaPessoaComNumeroSorteadoNaCartela);
	}

	private List<Usuario> listaPessoaComNumeroNaCartela(Integer numeroSorteado) {
		List<Usuario> lista = new ArrayList<Usuario>();
		Predicate<Usuario> predicado = usuario -> usuario.getCartela().getNumeros().stream().anyMatch(numero -> numero == numeroSorteado);
		lista = usuarios.stream().filter(predicado).collect(Collectors.toList());
		return lista;
	}

	@Override
	public List<Usuario> listaVencedores() {
		Integer cartelaCheia = Integer.parseInt(properties.getProperty("cartela.quantidadeMaxPermitida"));
		List<Integer> listaIdUsuarios = acoes.obterListaVencedores(cartelaCheia);
		if (listaIdUsuarios.isEmpty()) {
			return null;
		}
		List<Usuario> usuariosVencedores = usuarios.stream().filter(u -> listaIdUsuarios.contains(u.getId())).collect(Collectors.toList());
		return usuariosVencedores;
	}
	
	@Override
	public String numerosSortados() {
		StringBuilder sb = new StringBuilder();
		Set<Integer> numerosSorteados = sorteioNumero.getNumerosSorteados();
		int index = 0;
		for (Integer numero : numerosSorteados) {
			sb.append(numero);
			if (index != (numerosSorteados.size()-1)) {
				sb.append(", ");
			}
			index++;
		}
		return sb.toString();
	}

}
