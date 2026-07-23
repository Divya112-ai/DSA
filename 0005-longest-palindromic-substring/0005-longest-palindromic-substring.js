/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function(s) {
    let longest="";
    function checkPalindrome(left,right)
    {
        while(left>=0&&right<s.length&&s[left]===s[right])
        {
            left--;
            right++;
        }
        let palindrome=s.substring(left+1,right);
        if(palindrome.length>longest.length)
        {
            longest=palindrome;
        }
 
    }
    for(let i=0;i<s.length;i++)
    {
        checkPalindrome(i,i);
        checkPalindrome(i,i+1);
    }
    return longest;
};