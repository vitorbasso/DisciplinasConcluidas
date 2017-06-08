.include	"utils.asm"

.text

	addi	$t0, $zero, 42
	ImprimeInteiro ($t0)
	
	addi	$t0, $zero, 4
	ImprimeInteiro ($t0)
	
	LeInteiro
	move	$a0, $v0
	LeInteiro
	move	$a1, $v0
	
	Done