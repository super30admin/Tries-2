/*
TC : O(N)
SC : O(N)
*/

class Solution
{
public:
    vector<int> topKFrequent(vector<int> &nums, int k)
    {
        if (nums.size() == 0)
        {
            return {};
        }

        // key : nums[i], value : freq
        map<int, int> freqMap;

        for (int i = 0; i < nums.size(); i++)
        {
            freqMap[nums[i]]++;
        }

        vector<vector<int>> bucketList(nums.size() + 1);

        // add elememts in bucket based on freq
        for (auto i : freqMap)
        {
            bucketList[i.second].push_back(i.first);
        }

        for (int i = 0; i < bucketList.size(); i++)
        {
            for (int j = 0; j < bucketList[i].size(); j++)
            {
            }
        }

        vector<int> result;

        // get the k most freq elements
        for (int i = bucketList.size() - 1; i >= 0; i--)
        {
            if (bucketList[i].size() == 0)
            {
                continue;
            }
            for (int j = 0; j < bucketList[i].size(); j++)
            {
                result.push_back(bucketList[i][j]);
            }
            if (result.size() == k)
            {
                break;
            }
        }

        return result;
    }
};