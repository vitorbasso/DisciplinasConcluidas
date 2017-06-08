.data
inteiro:	.word 42
real:		.float 3.1415
duplo:		.double 2.7182818284590452353602874713526
enter:		.asciiz "\n"
hworld:		.asciiz "Oi mundo!"

.macro	done
	li	$v0, 10
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
li	$v0, 1	#codigo da syscall
add	$a0, $zero, $s0
syscall
printNewLine

#imprime inteiro em hexadecimal
li	$v0, 34	#codigo da syscall
add	$a0, $zero, $s0
syscall
printNewLine

#imprime inteiro em binário
li	$v0, 35	#codigo syscall
add	$a0, $zero, $s0
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

#Imprime uma string
li	$v0, 4
add	$a0, $zero, $s3
syscall
printNewLine

#lë inteiro
li	$v0, 5
syscall

#le float
li	$v0, 6
syscall

#le double
li	$v0, 7
syscall
done