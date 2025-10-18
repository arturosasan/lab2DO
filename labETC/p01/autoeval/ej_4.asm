.data 0x1000000C
var: .word 106
A:    .word  2000
chain: .asciiz "camioneta"

.text
.globl __start

__start:

la $t0, var
la $t1, chain
lw $s0, 0($t0)
lb $s1, 5($t1)
