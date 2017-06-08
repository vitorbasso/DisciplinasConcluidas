.data
va:	.word	42
vb:	.word	10
vc:	.word	0x000000FF
vd:	.word	0xFFF00000
enter:	.asciiz	"\n"

.macro newLine
li	$v0, 4
la	$a0, enter
syscall
.end_macro

.macro done
li	$a0, 10
syscall
.end_macro

.text
#carrega as variáveis nos registradores
la	$s0, va
lw	$s0, 0($s0)
la	$s1, vb
lw	$s1, 0($s1)
la	$s2, vc
lw	$s2, 0($s2)
la	$s3, vd
lw	$s3, 0($s3)

#add e suas variantes
add	$t0, $s0, $s1
addi	$t1, $s0, 10
addiu	$t2, $s0, 10
addu	$t3, $s0, $s1

li	$v0, 1
move	$a0, $t0
syscall
newLine
li	$v0, 1
move	$a0, $t1
syscall
newLine
li	$v0, 1
move	$a0, $t2
syscall
newLine
li	$v0, 1
move	$a0, $t3
syscall
newLine
li	$v0, 1
move	$a0, $s3
syscall
newLine
li	$v0, 1
move	$a0, $s2
syscall
newLine
