Developed with Java Version JDK 1.8 in the NETBEANS IDE 8.2.

HuffmanEncoding.java is the main class which will encode plain text and decode encoded text given a 	valid inputs
BinaryTree.java is a class for created binary trees
TreeNode.java is a class for the nodes on a Huffman Tree
HuffmanQueue.java is a priority queue utilized to create Huffman Tree
HuffmanCode.java encodes plain text and decodes encoded text

First argument - *.txt file input file containing a frequency table to be used for
	the Huffman encoding.  Format is:
	'A - 19'
	'B - 7'
	...

	Additional spaces between letter and dash, and dash and number will be ignored. Lowercase 	letters will be treated as uppercase.  All other symbols and missing '-' will cause error.

Second argument - *.txt output file name for the preorder traversal of the Huffman Tree and the
	encoding list.

Third argument - *.txt input file containing plain text to be encoded.  Lower case letters will be 	treated as uppercase and all other symbols will be ignored.

Fourth argument - *.txt output file name for the encoding of the inputter plain text.

Fifth argument - *.txt input file name containing encoded message to be decoded.  Only 1s and 0s 	allowed.  All other symbols will cause an error, included spaces between the binary code.
	Spaces at the beginning and end of lines, however, will be ignored.