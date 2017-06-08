.data
enter:	.asciiz	"\n"
p0:	.space	14	#22 realmente disponiveis
p1:	.space	14
str1:	.asciiz	"Insira o valor de p1.x:\n"
str2:	.asciiz	"Insira o valor de p1.y:\n"
str3:	.asciiz	"Insira o valor de p1.z:\n"

.macro newLine
	li	$v0, 4
	la	$a0, enter
	syscall
.end_macro

.macro done
	li	$v0, 10
	syscall
.end_macro

.macro	readInt
	li	$v0, 5
	syscall
.end_macro

.macro printInt (%int_value)
	li	$v0, 1
	move	$a0, %int_value
	syscall
	newLine
.end_macro

.macro	POP
	#Realiza a desmontagem da pilha
	sw	$s0, 0($sp)
	sw	$s1, 4($sp)
	sw	$a0, 8($sp)
	sw	$a1, 12($sp)
	addi	$sp, $sp, 12
	#-----------------------------
	#retorna o controle
.end_macro

.macro	PUSH
	#Realiza montagem da pilha
	lw	$s0, 0($sp)
	lw	$s1, 4($sp)
	lw	$a0, 8($sp)
	lw	$a1, 12($sp)
	addi	$sp, $sp, -12
	#--------------------------
.end_macro

.text
#Coloca os endereços de p1 e p2 em s0 e s1
	la	$s0, p0
	la	$s1, p1

#Chama funçao para ler p1.x, p1.y e p1.z
	move	$a0, $s0
	jal	lerElementos
	
#Calcula os valores para p2.x, p2.y e p2.z
	move	$a0, $s0
	move	$a1, $s1
	jal	calculaP2
	
#Printa as duas structs
	move	$a0, $s0
	move	$a1, $a1
	jal	printar
	
	done
	
lerElementos:
	PUSH
	move	$t7, $a0
	
	li	$v0, 4
	la	$a0, str1
	readInt
	lw	$v0, 0($t7)
	
	li	$v0, 4
	la	$a0, str2
	readInt
	lw	$v0, 4($t7)
	
	li	$v0, 4
	la	$a0, str3
	readInt
	lw	$v0, 8($t7)
	
	POP
	#retorna o controle
	jr	$ra
	
calculaP2:
	PUSH
	move	$t7, $a0
	move	$t8, $a1
	
	lw	$t0, 0($t7)
	lw	$t1, 4($t7)
	add	$t2, $t0, $t1
	sw	$t2, 0($t8)
	
	lw	$t0, 4($t7)
	lw	$t1, 8($t7)
	add	$t2, $t0, $t1
	sw	$t2, 4($t8)
	
	lw	$t0, 0($t7)
	lw	$t1, 8($t7)
	add	$t2, $t0, $t1
	sw	$t2, 8($t8)
	POP
	#retorna o controle
	jr	$ra
	
printar:
	PUSH
	move	$t7, $a0
	move	$t8, $a1
	
	lw	$t0, 0($t7)
	printInt($t0)
	
	lw	$t0, 4($t7)
	printInt($t0)
	
	lw	$t0, 8($t7)
	printInt($t0)
	
	lw	$t0, 0($t8)
	printInt($t0)
	
	lw	$t0, 4($t8)
	printInt($t0)
	
	lw	$t0, 8($t8)
	printInt($t0)
	POP
	#retorna o controle
	jr	$ra