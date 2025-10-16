.globl __start
 .text 0x00400000
__start: 
li $t0, 0x10003000
lw $t1, 2($t0)