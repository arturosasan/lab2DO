		.globl __start
		.text 0x00400000
__start:
		li $s1,0
		li $s0, 0
loop:
		li $v0,1
		addi $a0, $s1,1
		syscall
		li $v0,11
		li $a0,'>'
		syscall
		li $v0,5
		syscall
		beqz $v0, exit
		addu $s0,$s0,$v0
		addi $s1,$s1,1
		li $v0,11
		li $a0, '\n'
		syscall
		b loop
		
exit: 	li $v0,11
		li $a0,'='
		li $v0,1
		move $a0,$s0
		syscall
		li $v0,11
		li $a0,'\n'
		syscall
		li $v0,11
		li $a0,'n'
		syscall
		li $v0,11
		li $a0,'='
		syscall
		li $v0,1
		move $a0,$s1
		syscall
		li $v0,10
		syscall