#inverteArray.asm
#
#DESC: Lê um array de n números e subsequentemente inverte
#seus valores na memória
#
#DDA 27.09.16
#-----------------------------------------------------------

.data
str1:	.asciiz	"Quantos numeros:\n"
str2:	.asciiz	"Proximo numero:\n"
str3:	.asciiz	"O array invertido é:\n"
enter:	.asciiz	"\n"
tab:	.asciiz	"\t"

.macro	tab
	li	$v0, 4
	la	$a0, tab
	syscall
.end_macro

.macro newLine
	li	$v0, 4
	la	$a0, enter
	syscall
.end_macro

.text
	li	$v0, 4
	la	$a0, str1
	syscall
	newLine
	
	li	$v0, 5
	syscall
	move 	$s0, $v0	# $s0 tem quantos numeros tem a array - N
	
	sll	$t0, $s0, 2	# pega N multiplica por 4 e coloca em t0
	
	li	$v0, 9
	move	$a0, $t0
	syscall			#Aloca N*4 bytes na memoria = N words
	
	move	$s1, $v0	#Coloca o endereço dessa memória alocada em $s1
	
	move	$a0, $s0	#Coloca N em arg0 (a0)
	move	$a1, $s1	#Coloca o primeiro endereço de memória da memória alocada em arg1 (a1)
	jal	leNumeros	#Le os numeros do array
	newLine
	
	move	$a0, $s0
	move	$a1, $s1
	jal	imprimeArray
	newLine
	
	move	$a0, $s0
	move	$a1, $s1
	jal	inverteArray
	newLine
	
	move	$a0, $s0
	move	$a1, $s1
	jal	imprimeArray
	newLine
	
	li	$v0, 10
	syscall
	
	
leNumeros:
	#Primeiro cria-se a pilha com os backups
	addi	$sp, $sp, -12
	sw	$s0, 0($sp)
	sw	$s1, 4($sp)
	sw	$a0, 8($sp)
	sw	$a1, 12($sp)
	#----------------------------------------
	move	$t7, $a0	#Coloca cópia de N ($a0) em $t7
	move	$s0, $zero	#Usa $s0 como contador c = 0;
	#----------------------------Inicio FOR
FOR:	slt	$t0, $s0, $t7
	beq	$t0, $zero, FIM
	#----------------------------
	
	li	$v0, 4
	la	$a0, str2
	syscall
	
	li	$v0, 5
	syscall
	
	sw	$v0, 0($s1)
	
	#Prepara para voltar para o for
	addi	$s0, $s0, 1
	addi	$s1, $s1, 4
	j	FOR
	#-------------------------------
FIM:	move	$s0, $zero
	move	$s1, $a1
	move	$v0, $s1
	#POP da pilha----------------------------
	lw	$s0, 0($sp)
	lw	$s1, 4($sp)
	lw	$a0, 8($sp)
	lw	$a1, 12($sp)
	addi	$sp, $sp, 12
	#----------------------------------------
	jr	$ra
	
imprimeArray:
	#Primeiro cria-se a pilha com os backups
	addi	$sp, $sp, -12
	sw	$s0, 0($sp)
	sw	$s1, 4($sp)
	sw	$a0, 8($sp)
	sw	$a1, 12($sp)
	#----------------------------------------
	move	$t7, $a0
	move	$s0, $zero
	#---------------------------
FOR1:	slt	$t0, $s0, $t7
	beq	$t0, $zero, FIM1
	#---------------------------
	
	li	$v0, 1
	lw	$a0, 0($s1)
	syscall
	tab
	
	#Prepara proxima chamada
	addi	$s0, $s0, 1
	addi	$s1, $s1, 4
	j	FOR1
FIM1:	move	$s0, $zero
	move	$s1, $a1
	move	$v0, $zero
	#POP da pilha----------------------------
	lw	$s0, 0($sp)
	lw	$s1, 4($sp)
	lw	$a0, 8($sp)
	lw	$a1, 12($sp)
	addi	$sp, $sp, 12
	#----------------------------------------
	jr	$ra
	
inverteArray:
	#Primeiro cria-se a pilha com os backups
	addi	$sp, $sp, -12
	sw	$s0, 0($sp)
	sw	$s1, 4($sp)
	sw	$a0, 8($sp)
	sw	$a1, 12($sp)
	#----------------------------------------
	move	$t7, $a0
	sll	$t0, $s0, 2
	addi	$t0, $t0, -4
	li	$v0, 9
	move	$a0, $t0
	syscall
	move	$s2, $v0	#Cria uma segunda array para colocar os itens da primeira
	move	$s3, $s2	#Back up de s2
	add	$t1, $s1, $t0	#Cria o endereco onde esta o ultimo elemento de $s1
	move	$s0, $zero
	#-----------------------
FOR2:	slt	$t0, $s0, $t7
	beq	$t0, $zero, FIM2
	#-----------------------
	lw	$t2, 0($t1)
	sw	$t2, 0($s2)
	addi	$t1, $t1, -4
	addi	$s2, $s2, 4
	addi	$s0, $s0, 1
	j	FOR2
FIM2:	move	$s2, $s3
	move	$s0, $a0
	move	$s0, $zero
	move	$v0, $zero
	#POP da pilha----------------------------
	lw	$s0, 0($sp)
	lw	$s1, 4($sp)
	lw	$a0, 8($sp)
	lw	$a1, 12($sp)
	addi	$sp, $sp, 12
	#----------------------------------------
	move	$s1, $s2
	jr	$ra