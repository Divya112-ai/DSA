/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function(s, p) {
    const dp=(i,j)=>
    {
        if(j===p.length) return i===s.length;
        let match=i<s.length && (s[i]===p[j]|| p[j]===".")
        if(j+1<p.length && p[j+1]==="*")
        {
            return dp(i,j+2) || (match && dp(i+1,j));
        }
        return match && dp(i+1,j+1);
    };
    return dp(0,0)
};