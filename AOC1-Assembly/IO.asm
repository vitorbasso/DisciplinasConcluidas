.data
inteiro:	.word	42
real:		.float	3.1415
duplo:		.double	2.71828172845904523544
enter:		.asciiz	"\n"
hworld:		.asciiz "Hello World!\n"
sbuffer:	.space	30

.macro done
	li	$v0,10
	syscall
.end_macro

.macro	printNewLine
	li	$v0, 4
	la	$a0, enter
	syscall
.end_macro

.text
	la	$s0, inteiro
	lw	$s0, 0($s0)
	
	la	$s1, real
	lwc1	$f20, 0($s1)
	
	la	$s2, duplo
	lwc1	$f22, 0($s2)
	lwc1	$f23, 4($s2)
	
	la	$s3, hworld
	
#imprime inteiro
	li	$v0, 1
	move	$a0, $s0
	syscall
	printNewLine
#imprime inteiro em hexadecimal
	li	$v0, 34
	move	$a0, $s0
	syscall
	printNewLine
#imprime inteiro em binário
	li	$v0, 35
	move	$a0, $s0
	syscall
	printNewLine
#imprime um float
	li	$v0, 2
	mov.s	$f12, $f20
	syscall
	printNewLine
#imprime um double
	li	$v0, 3
	mov.d	$f12, $f22
	syscall	
	printNewLine
#imprime string
	li	$v0, 4
	move	$a0, $s3
	syscall
	printNewLine
	
#---------------------------------------------------------
#le inteiro
	li	$v0, 5
	syscall
	move	$a0, $v0
	li	$v0, 1
	syscall
	printNewLine
#le real
	li	$v0, 6
	syscall
	printNewLine
#le double
	li	$v0, 7
	syscall
	printNewLine
#le string
	li	$v0, 8
	la	$a0, sbuffer
	addi	$a1, $zero, 30
	syscall
	printNewLine
	
	done