import os

""" 
Renames the filenames within the same directory to be Unix friendly
(1) Changes spaces to hyphens
(2) Makes lowercase (not a Unix requirement, just looks better ;)
Usage:
python rename.py
"""

path =  os.getcwd()
filenames = os.listdir(path)
x=[]
for filename in filenames:
 if filename.endswith('.xml'):
  x.append(filename)


for filename in x:
 file_o=open(filename,'r')  
 content=file_o.read().splitlines(True)               
 f="""<annotation>
   <folder>image</folder>
   <filename>"""+filename[:-4]+""".jpg</filename>

 <source>

 """
 content[3]=f

 with open(filename, 'w') as fout:
   fout.writelines(content[3:])


