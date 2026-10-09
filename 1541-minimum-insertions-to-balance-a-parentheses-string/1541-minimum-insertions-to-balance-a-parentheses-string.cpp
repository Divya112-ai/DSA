class Solution {
public:
    int minInsertions(string s) {
        float open=0;
        int add=0;
        for(int i=0;i<s.length();++i)
        {
            if(s[i]=='(')
            {
                if(open-(int)open!=0)
                {
                    add+=1;
                    open-=0.5;
                }
                open+=1;
            }
            else
            {
                open-=0.5;
            }
            if(open<0)
            {
                open=0.5;
                ++add;
            }
        }
        add+=open*2;
        return add;
    }
};