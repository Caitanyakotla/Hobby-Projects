import api


class Solution:
    def __init__(self, arr):
        # If you need initialization code, you can write it here!
        # Do not remove.
        self.arr = arr

    def computing_function(self):
        """
        Return the start day and end day for the longest sequence of days where the mean 
        temperature is within five degrees.
        
        :rtype: tuple
        """
        arr = self.arr
        len_array = len(arr)
        init = 0
        longest = 0
        for i in range(init, len_array - 1):
            equation = arr[i + 1] - arr[init]
            if -5 <= equation <= 5 and len_array - 2 != i:
                end = i + 1
            else:
                if (end - init) > longest:
                    longest = end - init
                    k = init
                    l = end
                init = i + 1
        return (k, l)

    def get_start_day(self):
        """
        Return the start day for the longest sequence of days where the mean 
        temperature is within five degrees.
        
        :rtype: int
        """
        # Todo: Write your code here!
        (start_day, end_day) = self.computing_function()
        return start_day

    def get_end_day(self):
        """
        Return the end day for the longest sequence of days where the mean 
        temperature is within five degrees.
        
        Note that the start and end day of the sequence may be the same.
        
        :rtype: int
        """
        # Todo: Write your code here!
        (start_day, end_day) = self.computing_function()
        return end_day
