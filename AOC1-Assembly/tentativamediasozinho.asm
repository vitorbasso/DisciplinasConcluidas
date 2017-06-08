#Tentando fazer o programa que calcula media sozinho
.data
str1:	.asciiz	"Quantos numeros para a media?\n"
str2:	.asciiz	"Qual o proximo numero? \n"
str3:	.asciiz	"A media é: \n"

.macro	Done
	li	$v0, 10
	syscall
.end_macro

.text
#Pegar quantos numeros teráo para fazer o calculo
	li	$v0, 4
	la	$a0, str1
	syscall				#Coloca o codigo para imprimir string e coloca a string str1 em arg0 - a0. Entao chama-se o sistema
	
	li	$v0, 5
	syscall
	
	move	$s0, $v0		#Coloca o resultado de v0 - pegar int - em s0 ( Quantidade de numeros - N)
	
#Alocar espaço para uma array com os N numeros que serao lidos
	li	$v0, 9
	sll	$a0, $s0, 2		#Multiplica s0 (N) por 4 (N*4 = tamanho para se alocar - 1 word = 4 bytes) e coloca em arg0 (a0) da funçao 9 do sistema para alocar memória
	syscall				#Realiza o alocamento, colocando o endereço de memoria do inicio da array em v0
	move	$s1, $v0		#Coloca esse endereço da memoria no registrador s1
	
	move	$a0, $s0		#Coloca N como arg0 - a0
	move	$a1, $s1		#Coloca o endereço de memória do inicio da array em arg1 - a1
	
	jal	leECalcula		#Chama o subprocesso leECalcula e coloca o registrador $ra como PC+4 ( A próxima instruçao depois dessa - move $s2, $v0 - )
	
#Mostra o resultado
	move	$s2, $v0		#Coloca o resultado de leECalcula em s2
	
	li 	$v0, 4			#Codigo para imprimir string
	la	$a0, str3		#Coloca String em a0
	syscall
	
	li	$v0, 1			#Codigo para imprimir inteiro
	move	$a0, $s2		#Coloca s2 ( Resultado de leECalcula ) em arg0 - a0
	syscall
	
#Fim do programa - Finaliza usando o macro done
	Done

#Inicio da funçao leECalcula	
leECalcula:
	#Primeiro salva-se os valores nos registradores (que nao t) como backup ----------------------------------------------------
	sw	$s0, 0($sp)	#Salva s0 no primeiro endereco de memoria de stack pointer - sp ------ s0 = N
	sw	$s1, 4($sp)	#s1 = primeiro endereco do array de memoria alocada
	sw	$a0, 8($sp)	#a0 = N = s0
	sw	$a1, 12($sp)	#a1 = s1
	addi	$sp, $sp, -12	#Soma -16 a sp para que ele aponte para o ultimo elemento da pilha de back up
	#Fim da montagem da pilha de back up ---------------------------------------------------------------------------------------
	#Iniciando as condiçoes da funçao ------------------------------------------------------------------------------------------
	move	$s0, $zero	#coloca zero em s0 para que s0 sirva como contador => int i = 0; (em C)
	move 	$t7, $a0	#Coloca o N em t7 para que sirva como um back up => int temp = N - pois a0 sera usado e substituido no corpo
	#Cria o primeiro for que lerá os elementos de que se deve calcular a media----------------
FOR1:	slt	$t0, $s0, $a0	#Faz if(s0<ao) then t0 = 1; else t0 = 0; - sendo que s0 é o contador (iniciado em zero) e a0 é N (numero de elementos para calcular a media)
	beq	$t0, $zero, FIM1	#Verifica se t0 = 0 (o contador está igual a N) e pula para FIM1 se for o caso
	#Fim da condiçao do for ------------------------------------------------------------------
	#Inicio do corpo do for ------------------------------------------------------------------
	li	$v0, 4			#printa a string pedindo o proximo numero
	la	$a0, str2
	syscall
	li	$v0, 5			#le um inteiro
	syscall
	sw	$v0, 0($s1)		#Coloca o inteiro lido em s1 - apontando para um endereço do array de memória alocada
	
	#Arruma as variaveis de controle para a proxima iteraçao do FOR1-------------
	
	addi	$s1, $s1, 4		#Faz s1 apontar para o proximo endereço - ponteiro++
	move	$a0, $t7		#Retorna o valor N para o registrador a0
	addi	$s0, $s0, 1		#Soma um ao contador => i++
	j	FOR1			#Retorna para FOR1 - até que beq pule para FIM1
FIM1:	move	$s0, $zero		#Reseta o contador para zero
	move	$s1, $a1		#Reseta o s1 para o primeiro endereço do array de memória alocada
	move	$v0, $zero		#Coloca o return como zero
	#Inicio do FOR2 que fara a soma de todos os numeros na array -------------------------------
FOR2:	slt	$t0, $s0, $a0
	beq	$t0, $zero, FIM2
	#-------------------------------------------------------------------------------------------
	lw	$t1, 0($s1)		#Coloca em t1 o valor que s1 esta apontando atualmente ( sendo que s1 aponta para um endereço de memoria no array de memoria alocado)
	add	$v0, $v0, $t1		#Soma a resposta com t1 => s0+=t1
	#Arruma as variaveis de controle para a proxima iteraçao do FOR2------------
	addi	$s1, $s1, 4
	addi	$s0, $s0, 1
	j	FOR2
FIM2:	div	$v0, $a0
	mflo	$v0
	mfhi	$v1
	#Retirar os elementos da pilha de back up e devolve-los para seus registradores
	lw	$s0, 0($sp)
	lw	$s1, 4($sp)
	lw	$a0, 8($sp)
	lw	$a1, 12($sp)
	addi	$sp, $sp, 12
	#Voltando para o processo que chamou usando o registro apontador ra
	jr	$ra