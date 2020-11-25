class Funciones_apoyo
	def function_fx (x)
		return (x*x)
	end

	def function_fx2 (x)
		return (x*x*x)
	end

	def insertardatosTXT(datos)
		
		File.open('D:\ULima Virtual\Lenguajes de programación\ProyectoRUBI\ruby\Elixir\resultadosX.txt', 'w') do |f2|
			
		f2.puts datos

		end
	end

	def leerTXT()
		#leer documentos del elixir
		File.open('D:\ULima Virtual\Lenguajes de programación\ProyectoRUBI\ruby\Elixir\resultadosX.txt', 'r') do |f1|
			while linea = f1.gets
		puts linea
			end
		end
	end
end
