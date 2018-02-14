<%@page import="java.util.ArrayList"%>
<div class="left-sidebar">
						<h2>Categorías</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<%! 
								ArrayList<Categoria> lista = CategoriaDAO.listar();
								int codigo = 0;
							%>
							<% 
								for(int i = 0; i < lista.size(); i++) { 
								codigo = lista.get(i).getCodigo();
							%>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a <% if(CategoriaDAO.esSuperior(codigo)) {%>data-toggle="collapse" data-parent="#accordian" <% } %> href="#<%=codigo%>">
											<% if(CategoriaDAO.esSuperior(codigo)) {%><span class="badge pull-right"><i class="fa fa-plus"></i></span><% } %> 
											<a href="?category=<%=codigo%>"><%=lista.get(i).getNombre() %></a>
										</a>
									</h4>
								</div>
								<div id="<%=codigo %>" class="panel-collapse collapse">			
									<div class="panel-body">
									<% ArrayList<Categoria> sublista = CategoriaDAO.listarSub(codigo);  %>
										<ul>
											<% for(int j = 0; j < sublista.size(); j++) { %>
											<li><a href="?category=<%=codigo%>"><%= sublista.get(j).getNombre() %></a></li>
											<%} %>
										</ul>
									</div>
									
								</div>
							</div>
						 	<% } %>
						</div><!--/category-products-->
					
						<div class="brands_products"><!--brands_products-->
							<h2>Marcas</h2>
							<%! ArrayList<Marca> listaMarca = MarcaDAO.listarTodoDeMarcas(); %>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
									<% 
									for(int k = 0; k < listaMarca.size(); k++) {
										int codigoMarca = listaMarca.get(k).getCodigo();
									%>
									<li><a href="?brand=<%=codigoMarca%>"> <span class="pull-right">(<%=MarcaDAO.contarMarcas(codigoMarca) %>)</span><%=listaMarca.get(k).getNombre() %></a></li>
									<% } %>
								</ul>
							</div>
						</div><!--/brands_products-->

						<div class="shipping text-center"><!--shipping-->
							<img src="images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
					
					</div>